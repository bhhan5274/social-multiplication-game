package com.bhhan.testutils.beans;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by hbh5274@gmail.com on 2020-07-22
 * Github : http://github.com/bhhan5274
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class LeaderBoardPosition {
    private Long userId;
    private Long totalScore;
}
