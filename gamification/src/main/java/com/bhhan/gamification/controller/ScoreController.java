package com.bhhan.gamification.controller;

import com.bhhan.gamification.domain.ScoreCard;
import com.bhhan.gamification.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hbh5274@gmail.com on 2020-07-14
 * Github : http://github.com/bhhan5274
 */

@RestController
@RequestMapping("/scores")
@RequiredArgsConstructor
public class ScoreController {
    private final GameService gameService;

    @GetMapping("/{attemptId}")
    public ScoreCard getScoreForAttempt(@PathVariable("attemptId") Long attemptId){
        return gameService.getScoreForAttempt(attemptId);
    }
}
