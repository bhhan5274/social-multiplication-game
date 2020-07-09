package com.bhhan.multiplication.repository;

import com.bhhan.multiplication.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by hbh5274@gmail.com on 2020-07-08
 * Github : http://github.com/bhhan5274
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAlias(String alias);
}
