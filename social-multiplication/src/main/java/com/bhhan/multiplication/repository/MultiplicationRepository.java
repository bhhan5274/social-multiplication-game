package com.bhhan.multiplication.repository;

import com.bhhan.multiplication.domain.Multiplication;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hbh5274@gmail.com on 2020-07-08
 * Github : http://github.com/bhhan5274
 */
public interface MultiplicationRepository extends JpaRepository<Multiplication, Long> {
}
