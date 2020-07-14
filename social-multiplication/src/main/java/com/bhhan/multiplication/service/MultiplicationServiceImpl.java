package com.bhhan.multiplication.service;

import com.bhhan.multiplication.domain.Multiplication;
import com.bhhan.multiplication.domain.MultiplicationResultAttempt;
import com.bhhan.multiplication.domain.User;
import com.bhhan.multiplication.event.EventDispatcher;
import com.bhhan.multiplication.event.MultiplicationSolvedEvent;
import com.bhhan.multiplication.repository.MultiplicationResultAttemptRepository;
import com.bhhan.multiplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

/**
 * Created by hbh5274@gmail.com on 2020-07-08
 * Github : http://github.com/bhhan5274
 */

@Service
@RequiredArgsConstructor
@Transactional
public class MultiplicationServiceImpl implements MultiplicationService{

    private final RandomGeneratorService randomGeneratorService;
    private final MultiplicationResultAttemptRepository attemptRepository;
    private final UserRepository userRepository;
    private final EventDispatcher eventDispatcher;

    @Override
    public Multiplication createRandomMultiplication() {
        final int factorA = randomGeneratorService.generateRandomFactor();
        final int factorB = randomGeneratorService.generateRandomFactor();
        return Multiplication.builder()
                .factorA(factorA)
                .factorB(factorB)
                .build();
    }

    @Override
    public MultiplicationResultAttempt checkAttempt(MultiplicationResultAttempt resultAttempt) {
        final Optional<User> user = userRepository.findByAlias(resultAttempt.getUser().getAlias());

        Assert.isTrue(!resultAttempt.isCorrect(), "You can't send on attempt marked as correct!!!");

        boolean isCorrect = resultAttempt.getResultAttempt() == resultAttempt.getMultiplication().getFactorA() * resultAttempt.getMultiplication().getFactorB();
        final MultiplicationResultAttempt checkedAttempt = MultiplicationResultAttempt.builder()
                .user(user.orElse(resultAttempt.getUser()))
                .multiplication(resultAttempt.getMultiplication())
                .resultAttempt(resultAttempt.getResultAttempt())
                .correct(isCorrect)
                .build();


        final MultiplicationResultAttempt storedAttempt = attemptRepository.save(checkedAttempt);

        eventDispatcher.send(
                MultiplicationSolvedEvent.builder()
                .multiplicationResultAttemptId(storedAttempt.getId())
                .userId(storedAttempt.getUser().getId())
                .correct(storedAttempt.isCorrect())
                .build()
        );

        return storedAttempt;
    }

    @Override
    public List<MultiplicationResultAttempt> getStatsForUser(String userAlias) {
        return attemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias);
    }

    @Override
    public MultiplicationResultAttempt getResultById(Long resultId) {
        return attemptRepository.findById(resultId)
                .orElseThrow(() -> new IllegalArgumentException("요청한 resultId [" + resultId + "] 는 존재하지 않습니다."));
    }
}
