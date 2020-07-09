package com.bhhan.multiplication.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by hbh5274@gmail.com on 2020-07-08
 * Github : http://github.com/bhhan5274
 */

@Entity
@Table(name = "MULTIPLICATION_RESULT_ATTEMPTS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MultiplicationResultAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MULTIPLICATION_RESULT_ATTEMPT_ID")
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "MULTIPLICATION_ID")
    private Multiplication multiplication;

    private int resultAttempt;
    private boolean correct;

    @Builder
    public MultiplicationResultAttempt(Long id, User user, Multiplication multiplication, int resultAttempt, boolean correct){
        this.id = id;
        this.user = user;
        this.multiplication = multiplication;
        this.resultAttempt = resultAttempt;
        this.correct = correct;
    }
}
