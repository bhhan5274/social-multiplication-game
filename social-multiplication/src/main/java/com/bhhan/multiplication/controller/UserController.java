package com.bhhan.multiplication.controller;

import com.bhhan.multiplication.domain.User;
import com.bhhan.multiplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hbh5274@gmail.com on 2020-07-22
 * Github : http://github.com/bhhan5274
 */

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable("userId") final Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "요청한 userId [" + userId + "] 는 존재하지 않습니다."));
    }
}
