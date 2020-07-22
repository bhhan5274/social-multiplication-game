package com.bhhan.testutils.beans;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2020-07-22
 * Github : http://github.com/bhhan5274
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class Stats {
    private List<String> badges;
    private int score;
}
