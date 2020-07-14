package com.bhhan.gamification.event;

import com.bhhan.gamification.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by hbh5274@gmail.com on 2020-07-14
 * Github : http://github.com/bhhan5274
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class EventHandler {
    private final GameService gameService;

    @RabbitListener(queues = "${multiplication.queue}")
    public void handleMultiplicationSolvedEvent(final MultiplicationSolvedEvent event){
        log.info("Multiplication Solved Event 수신: {}", event.getMultiplicationResultAttemptId());
        try{
            gameService.newAttemptForUser(event.getUserId(),
                    event.getMultiplicationResultAttemptId(),
                    event.isCorrect());
        }catch(Exception e){
            log.error("MultiplicationSolvedEvent 처리 시 에러", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
