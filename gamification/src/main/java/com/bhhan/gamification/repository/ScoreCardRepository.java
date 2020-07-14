package com.bhhan.gamification.repository;

import com.bhhan.gamification.domain.LeaderBoardRow;
import com.bhhan.gamification.domain.ScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2020-07-14
 * Github : http://github.com/bhhan5274
 */
public interface ScoreCardRepository extends JpaRepository<ScoreCard, Long> {

    @Query("SELECT SUM(s.score) FROM com.bhhan.gamification.domain.ScoreCard s WHERE s.userId = :userId GROUP BY s.userId")
    Integer getTotalScoreForUser(@Param("userId") final Long userId);

    @Query("SELECT NEW com.bhhan.gamification.domain.LeaderBoardRow(s.userId, SUM(s.score)) " +
            "FROM com.bhhan.gamification.domain.ScoreCard s GROUP BY s.userId ORDER BY SUM(s.score) DESC")
    List<LeaderBoardRow> findFirst10();

    List<ScoreCard> findByUserIdOrderByScoreTimestampDesc(final Long userId);
    ScoreCard findByAttemptId(final Long attemptId);
}
