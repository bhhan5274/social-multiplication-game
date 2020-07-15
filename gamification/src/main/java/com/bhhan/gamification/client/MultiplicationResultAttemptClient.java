package com.bhhan.gamification.client;

import com.bhhan.gamification.client.dto.MultiplicationResultAttempt;

/**
 * Created by hbh5274@gmail.com on 2020-07-15
 * Github : http://github.com/bhhan5274
 */
public interface MultiplicationResultAttemptClient {
    MultiplicationResultAttempt retrieveMultiplicationResultAttemptById(final Long multiplicationId);
}
