package com.bhhan.multiplication.controller;

import com.bhhan.multiplication.domain.Multiplication;
import com.bhhan.multiplication.service.MultiplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hbh5274@gmail.com on 2020-07-09
 * Github : http://github.com/bhhan5274
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/multiplications")
public class MultiplicationController {
    private final MultiplicationService multiplicationService;

    @Value("${server.port}")
    private int serverPort;

    @GetMapping("/random")
    public Multiplication getRandomMultiplication(){
        log.info("무작위 곰셉을 생성한 서버 {}", serverPort);
        return multiplicationService.createRandomMultiplication();
    }
}
