package com.bhhan.multiplication.controller;

import com.bhhan.multiplication.domain.MultiplicationResultAttempt;
import com.bhhan.multiplication.service.MultiplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2020-07-09
 * Github : http://github.com/bhhan5274
 */

@Slf4j
@RestController
@RequestMapping("/results")
@RequiredArgsConstructor
public class MultiplicationResultAttemptController {
    private final MultiplicationService multiplicationService;

    @Value("${server.port}")
    private int serverPort;

    @PostMapping
    public ResponseEntity<MultiplicationResultAttempt> postResult(@RequestBody MultiplicationResultAttempt resultAttempt){
        try{
            return ResponseEntity.ok(multiplicationService.checkAttempt(resultAttempt));
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MultiplicationResultAttempt> getStatistics(@RequestParam("alias") String alias){
        return multiplicationService.getStatsForUser(alias);
    }

    @GetMapping("/{resultId}")
    @ResponseStatus(HttpStatus.OK)
    public MultiplicationResultAttempt getResultById(@PathVariable("resultId") Long resultId){
        log.info("조회 결과 {} 조회한 서버 @ {}", resultId, serverPort);
        return multiplicationService.getResultById(resultId);
    }
}
