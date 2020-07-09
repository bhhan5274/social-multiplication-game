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
@Table(name = "MULTIPLICATIONS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Multiplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MULTIPLICATION_ID")
    private Long id;

    private int factorA;
    private int factorB;

    @Builder
    public Multiplication(Long id, int factorA, int factorB){
        this.id = id;
        this.factorA = factorA;
        this.factorB = factorB;
    }
}
