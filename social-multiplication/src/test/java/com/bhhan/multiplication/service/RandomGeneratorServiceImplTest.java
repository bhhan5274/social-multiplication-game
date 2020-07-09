package com.bhhan.multiplication.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by hbh5274@gmail.com on 2020-07-09
 * Github : http://github.com/bhhan5274
 */
class RandomGeneratorServiceImplTest {
    private RandomGeneratorService randomGeneratorService;

    @BeforeEach
    void setUp(){
        randomGeneratorService = new RandomGeneratorServiceImpl();
    }

    @Test
    void generateRandomFactorIsBetweenExpectedLimits(){
        final List<Integer> randomFactors = IntStream.range(0, 1000)
                .map(i -> randomGeneratorService.generateRandomFactor())
                .boxed()
                .collect(Collectors.toList());

        assertThat(randomFactors)
                .containsOnlyElementsOf(IntStream.range(11, 100)
                .boxed()
                .collect(Collectors.toList()));
    }
}