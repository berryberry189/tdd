package com.book.tdd.chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 매달 비용을 지불해야하는 유료 서비스
public class ExpiryDateCalculatorTest {

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨(){

        assertExpiryDate(LocalDate.of(2021, 12, 18), 10000,
                LocalDate.of(2022, 1, 18));
        assertExpiryDate(LocalDate.of(2021, 11, 18), 10000,
                LocalDate.of(2021, 12, 18));
    }

    private void assertExpiryDate(LocalDate billingDate, int payAmount, LocalDate expectedExpiryDate){
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expiryDate =  cal.calculateExpiryDate(billingDate, payAmount);
        assertEquals(expectedExpiryDate, expiryDate);
    }

}
