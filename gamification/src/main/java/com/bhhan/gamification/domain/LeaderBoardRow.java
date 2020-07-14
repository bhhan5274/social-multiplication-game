package com.bhhan.gamification.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by hbh5274@gmail.com on 2020-07-14
 * Github : http://github.com/bhhan5274
 */

@Getter
public class LeaderBoardRow {
    private Long userId;
    private Long totalScore;

    @Builder
    public LeaderBoardRow(Long userId, Long totalScore){
        this.userId = userId;
        this.totalScore = totalScore;
    }
}
