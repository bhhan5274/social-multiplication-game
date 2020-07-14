package com.bhhan.gamification.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by hbh5274@gmail.com on 2020-07-14
 * Github : http://github.com/bhhan5274
 */

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class MultiplicationSolvedEvent implements Serializable {
    private Long multiplicationResultAttemptId;
    private Long userId;
    private boolean correct;
}
