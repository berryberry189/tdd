package com.book.tdd.chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 매달 비용을 지불해야하는 유료 서비스
public class ExpiryDateCalculatorTest {

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨(){
        assertExpiryDate(PayData.builder()
                                .billingDate(LocalDate.of(2021, 12, 18))
                                .payAmount(10000)
                                .build(),
                LocalDate.of(2022, 1, 18));
        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2021, 11, 18))
                        .payAmount(10000)
                        .build(),
                LocalDate.of(2021, 12, 18));
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음(){
        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2021, 1, 31))
                        .payAmount(10000)
                        .build(),
                LocalDate.of(2021, 2, 28));
        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2021, 5, 31))
                        .payAmount(10000)
                        .build(),
                LocalDate.of(2021, 6, 30));
        assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2020, 1, 31))
                        .payAmount(10000)
                        .build(),
                LocalDate.of(2020, 2, 29));
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate){
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expiryDate =  cal.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate, expiryDate);
    }

}
