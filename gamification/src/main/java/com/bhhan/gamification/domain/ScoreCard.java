package com.bhhan.gamification.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by hbh5274@gmail.com on 2020-07-14
 * Github : http://github.com/bhhan5274
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "SCORE_CARDS")
@Entity
public class ScoreCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCORE_CARD_ID")
    private Long id;

    private Long userId;
    private Long attemptId;
    private Long scoreTimestamp;
    private Integer score;

    @Builder
    public ScoreCard(Long id, Long userId, Long attemptId, Long scoreTimestamp, Integer score){
        this.id = id;
        this.userId = userId;
        this.attemptId = attemptId;
        this.scoreTimestamp = scoreTimestamp;
        this.score = score;
    }
}
