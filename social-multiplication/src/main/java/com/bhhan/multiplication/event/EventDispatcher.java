package com.bhhan.multiplication.event;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by hbh5274@gmail.com on 2020-07-14
 * Github : http://github.com/bhhan5274
 */

@Component
@RequiredArgsConstructor
public class EventDispatcher {
    private final RabbitTemplate rabbitTemplate;

    @Value("${multiplication.exchange}")
    private String multiplicationExchange;

    @Value("${multiplication.solved.key}")
    private String multiplicationSolvedRoutingKey;

    public void send(final MultiplicationSolvedEvent multiplicationSolvedEvent){
        rabbitTemplate.convertAndSend(
                multiplicationExchange,
                multiplicationSolvedRoutingKey,
                multiplicationSolvedEvent
        );
    }
}
