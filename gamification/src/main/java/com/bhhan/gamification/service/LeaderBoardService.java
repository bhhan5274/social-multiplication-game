package com.bhhan.gamification.service;

import com.bhhan.gamification.domain.LeaderBoardRow;

import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2020-07-14
 * Github : http://github.com/bhhan5274
 */
public interface LeaderBoardService {
    List<LeaderBoardRow> getCurrentLeaderBoard();
}
