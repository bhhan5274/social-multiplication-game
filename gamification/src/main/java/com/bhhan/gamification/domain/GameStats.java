package com.bhhan.gamification.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2020-07-14
 * Github : http://github.com/bhhan5274
 */

@Getter
public class GameStats {
    private Long userId;
    private Integer score;
    private List<BadgeCard.Badge> badges;

    public static GameStats emptyStats(Long userId){
        return new GameStats(userId, 0, Collections.EMPTY_LIST);
    }

    @Builder
    public GameStats(Long userId, Integer score, List<BadgeCard.Badge> badges){
        this.userId = userId;
        this.score = score;
        this.badges = badges;
    }
}
