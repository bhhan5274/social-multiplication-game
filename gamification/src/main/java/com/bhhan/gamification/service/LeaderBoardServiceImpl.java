package com.bhhan.gamification.service;

import com.bhhan.gamification.domain.LeaderBoardRow;
import com.bhhan.gamification.repository.ScoreCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2020-07-14
 * Github : http://github.com/bhhan5274
 */

@RequiredArgsConstructor
@Service
@Slf4j
public class LeaderBoardServiceImpl implements LeaderBoardService{

    private final ScoreCardRepository scoreCardRepository;

    @Override
    public List<LeaderBoardRow> getCurrentLeaderBoard() {
        return scoreCardRepository.findFirst10();
    }
}
