package com.book.tdd.chap02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    /**
     * TDD는 기능을 검증하는 코드를 먼저 작성하고 테스트를 통과시키기 위해 개발을 진행한다
     *
     */

    @Test
    void plus(){
        int result = Calculator.plus(1,2);
        assertEquals(3, result);
        assertEquals(5, Calculator.plus(4,1));
    }
}
