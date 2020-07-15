package com.bhhan.gamification.client;

import com.bhhan.gamification.client.dto.MultiplicationResultAttempt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by hbh5274@gmail.com on 2020-07-15
 * Github : http://github.com/bhhan5274
 */

@Component
@Slf4j
@RequiredArgsConstructor
public class MultiplicationResultAttemptClientImpl implements MultiplicationResultAttemptClient{

    private final RestTemplate restTemplate;

    @Value("${multiplicationHost}")
    private String multiplicationHost;

    @Override
    public MultiplicationResultAttempt retrieveMultiplicationResultAttemptById(Long multiplicationResultAttemptId) {
        return restTemplate.getForObject(multiplicationHost + multiplicationResultAttemptId,
                MultiplicationResultAttempt.class);
    }
}
