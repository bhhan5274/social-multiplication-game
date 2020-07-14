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

@Entity
@Table(name = "BADGE_CARDS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BadgeCard {

    public enum Badge {
        BRONZE_MULTIPLICATION,
        SILVER_MULTIPLICATION,
        GOLD_MULTIPLICATION,

        FIRST_ATTEMPT,
        FIRST_WON,
        LUCKY_NUMBER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BADGE_CARD_ID")
    private Long id;

    private Long userId;
    private Long badgeTimestamp;

    @Enumerated(EnumType.STRING)
    private Badge badge;

    @Builder
    public BadgeCard(Long id, Long userId, Long badgeTimestamp, Badge badge){
        this.id = id;
        this.userId = userId;
        this.badgeTimestamp = badgeTimestamp;
        this.badge = badge;
    }
}
