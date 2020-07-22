package com.bhhan.gamification.service;

import com.bhhan.gamification.repository.BadgeCardRepository;
import com.bhhan.gamification.repository.ScoreCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by hbh5274@gmail.com on 2020-07-22
 * Github : http://github.com/bhhan5274
 */

@Profile("test")
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final BadgeCardRepository badgeCardRepository;
    private final ScoreCardRepository scoreCardRepository;

    @Override
    public void deleteDatabaseContents() {
        scoreCardRepository.deleteAll();
        badgeCardRepository.deleteAll();
    }
}
