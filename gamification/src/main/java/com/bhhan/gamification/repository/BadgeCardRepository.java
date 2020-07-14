package com.bhhan.gamification.repository;

import com.bhhan.gamification.domain.BadgeCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2020-07-14
 * Github : http://github.com/bhhan5274
 */
public interface BadgeCardRepository extends JpaRepository<BadgeCard, Long> {
    List<BadgeCard> findByUserIdOrderByBadgeTimestampDesc(final Long userId);
}
