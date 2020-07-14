package com.bhhan.gamification.service;

import com.bhhan.gamification.domain.BadgeCard;
import com.bhhan.gamification.domain.GameStats;
import com.bhhan.gamification.domain.ScoreCard;
import com.bhhan.gamification.repository.BadgeCardRepository;
import com.bhhan.gamification.repository.ScoreCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by hbh5274@gmail.com on 2020-07-14
 * Github : http://github.com/bhhan5274
 */

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class GameServiceImpl implements GameService{

    private static final int LUCKY_NUMBER = 42;
    private static final int DEFAULT_SCORE = 10;

    private final ScoreCardRepository scoreCardRepository;
    private final BadgeCardRepository badgeCardRepository;

    @Override
    public GameStats newAttemptForUser(Long userId, Long attemptId, boolean correct) {
        if(correct){
            final ScoreCard scoreCard = ScoreCard.builder()
                    .score(DEFAULT_SCORE)
                    .userId(userId)
                    .attemptId(attemptId)
                    .scoreTimestamp(Instant.now().toEpochMilli())
                    .build();

            scoreCardRepository.save(scoreCard);
            log.info("사용자 ID {}, 점수 {} 점, 답안 ID {}",
                    userId, scoreCard.getScore(), attemptId);

            List<BadgeCard> badgeCards = processForBadges(userId, attemptId);
            return GameStats.builder()
                    .userId(userId)
                    .badges(badgeCards.stream().map(BadgeCard::getBadge).collect(Collectors.toList()))
                    .score(scoreCard.getScore())
                    .build();
        }
        return GameStats.emptyStats(userId);
    }

    @Override
    public GameStats retrieveStatsForUser(Long userId) {
        final Integer score = scoreCardRepository.getTotalScoreForUser(userId);
        if(score == null){
            return GameStats.emptyStats(userId);
        }
        final List<BadgeCard> badgeCards = badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId);
        return GameStats.builder()
                .userId(userId)
                .score(score)
                .badges(badgeCards.stream().map(BadgeCard::getBadge).collect(Collectors.toList()))
                .build();
    }

    @Override
    public ScoreCard getScoreForAttempt(Long attemptId) {
        return scoreCardRepository.findByAttemptId(attemptId);
    }

    private List<BadgeCard> processForBadges(Long userId, Long attemptId) {
        List<BadgeCard> badgeCards = new ArrayList<>();

        final Integer totalScore = scoreCardRepository.getTotalScoreForUser(userId);
        log.info("사용자 ID {} 의 새로운 점수 {}", userId, totalScore);

        final List<ScoreCard> scoreCardList = scoreCardRepository.findByUserIdOrderByScoreTimestampDesc(userId);
        final List<BadgeCard> badgeCardList = badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId);

        checkAndGiveBadgeBasedOnScore(badgeCardList,
                BadgeCard.Badge.BRONZE_MULTIPLICATION, totalScore, 100, userId)
                .ifPresent(badgeCards::add);
        checkAndGiveBadgeBasedOnScore(badgeCardList,
                BadgeCard.Badge.SILVER_MULTIPLICATION, totalScore, 500, userId)
                .ifPresent(badgeCards::add);
        checkAndGiveBadgeBasedOnScore(badgeCardList,
                BadgeCard.Badge.GOLD_MULTIPLICATION, totalScore, 999, userId)
                .ifPresent(badgeCards::add);

        if(scoreCardList.size() == 1 && !containsBadge(badgeCards, BadgeCard.Badge.FIRST_WON)){
            final BadgeCard firstWonBadge = giveBadgeToUser(BadgeCard.Badge.FIRST_WON, userId);
            badgeCards.add(firstWonBadge);
        }

        return badgeCards;
    }

    private Optional<BadgeCard> checkAndGiveBadgeBasedOnScore(List<BadgeCard> badgeCards, BadgeCard.Badge badge, Integer score, int scoreThreshold, Long userId) {
        if(score >= scoreThreshold && !containsBadge(badgeCards, badge)){
            return Optional.of(giveBadgeToUser(badge, userId));
        }
        return Optional.empty();
    }

    private boolean containsBadge(List<BadgeCard> badgeCards, BadgeCard.Badge badge) {
        return badgeCards.stream().anyMatch(b -> b.getBadge().equals(badge));
    }

    private BadgeCard giveBadgeToUser(BadgeCard.Badge badge, Long userId) {
        final BadgeCard badgeCard = BadgeCard.builder()
                .userId(userId)
                .badge(badge)
                .build();

        log.info("사용자 ID {} 새로운 배지 획득: {}", userId, badge);

        return badgeCardRepository.save(badgeCard);
    }
}
