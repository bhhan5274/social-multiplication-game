package com.bhhan.gamification.service;

import com.bhhan.gamification.domain.GameStats;
import com.bhhan.gamification.domain.ScoreCard;

/**
 * Created by hbh5274@gmail.com on 2020-07-14
 * Github : http://github.com/bhhan5274
 */
public interface GameService {
    GameStats newAttemptForUser(Long userId, Long attemptId, boolean correct);
    GameStats retrieveStatsForUser(Long userId);
    ScoreCard getScoreForAttempt(Long attemptId);
}
