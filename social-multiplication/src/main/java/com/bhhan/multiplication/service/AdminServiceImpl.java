package com.bhhan.multiplication.service;

import com.bhhan.multiplication.repository.MultiplicationRepository;
import com.bhhan.multiplication.repository.MultiplicationResultAttemptRepository;
import com.bhhan.multiplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by hbh5274@gmail.com on 2020-07-22
 * Github : http://github.com/bhhan5274
 */

@Profile("test")
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final MultiplicationRepository multiplicationRepository;
    private final MultiplicationResultAttemptRepository attemptRepository;
    private final UserRepository userRepository;

    @Override
    public void deleteDatabaseContents() {
        attemptRepository.deleteAll();
        multiplicationRepository.deleteAll();
        userRepository.deleteAll();
    }
}
