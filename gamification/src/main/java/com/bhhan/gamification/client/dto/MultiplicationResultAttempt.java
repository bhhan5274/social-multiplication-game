package com.bhhan.gamification.client.dto;

import com.bhhan.gamification.client.MultiplicationResultAttemptDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

/**
 * Created by hbh5274@gmail.com on 2020-07-15
 * Github : http://github.com/bhhan5274
 */

@Getter
@NoArgsConstructor
@Setter
@EqualsAndHashCode
@JsonDeserialize(using = MultiplicationResultAttemptDeserializer.class)
public class MultiplicationResultAttempt {
    private String userAlias;
    private int multiplicationFactorA;
    private int multiplicationFactorB;
    private int resultAttempt;
    private boolean correct;

    @Builder
    public MultiplicationResultAttempt(String userAlias, int multiplicationFactorA, int multiplicationFactorB,
                                       int resultAttempt, boolean correct){
        this.userAlias = userAlias;
        this.multiplicationFactorA = multiplicationFactorA;
        this.multiplicationFactorB = multiplicationFactorB;
        this.resultAttempt = resultAttempt;
        this.correct = correct;
    }
}
