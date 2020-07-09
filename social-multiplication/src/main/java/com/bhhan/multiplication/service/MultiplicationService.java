package com.bhhan.multiplication.service;

import com.bhhan.multiplication.domain.Multiplication;
import com.bhhan.multiplication.domain.MultiplicationResultAttempt;

import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2020-07-08
 * Github : http://github.com/bhhan5274
 */
public interface MultiplicationService {
    Multiplication createRandomMultiplication();
    MultiplicationResultAttempt checkAttempt(MultiplicationResultAttempt resultAttempt);
    List<MultiplicationResultAttempt> getStatsForUser(String userAlias);
    MultiplicationResultAttempt getResultById(Long resultId);
}
