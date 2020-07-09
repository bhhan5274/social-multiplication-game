package com.bhhan.multiplication.service;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by hbh5274@gmail.com on 2020-07-08
 * Github : http://github.com/bhhan5274
 */

@Service
public class RandomGeneratorServiceImpl implements RandomGeneratorService{

    private static final int MINIMUM_FACTOR = 11;
    private static final int MAXIMUM_FACTOR = 99;

    @Override
    public int generateRandomFactor() {
        return new Random()
                    .nextInt((MAXIMUM_FACTOR - MINIMUM_FACTOR) + 1) + MINIMUM_FACTOR;
    }
}
