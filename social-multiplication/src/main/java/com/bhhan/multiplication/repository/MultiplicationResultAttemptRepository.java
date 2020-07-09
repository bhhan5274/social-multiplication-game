package com.bhhan.multiplication.repository;

import com.bhhan.multiplication.domain.MultiplicationResultAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2020-07-08
 * Github : http://github.com/bhhan5274
 */
public interface MultiplicationResultAttemptRepository extends JpaRepository<MultiplicationResultAttempt, Long> {
    List<MultiplicationResultAttempt> findTop5ByUserAliasOrderByIdDesc(String userAlias);
}
