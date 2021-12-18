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

    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부(){
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2021, 1, 31))
                .billingDate(LocalDate.of(2021, 2, 28))
                .payAmount(10000)
                .build();
        assertExpiryDate(payData, LocalDate.of(2021, 3, 31));

        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2021, 1, 30))
                .billingDate(LocalDate.of(2021, 2, 28))
                .payAmount(10000)
                .build();
        assertExpiryDate(payData2, LocalDate.of(2021, 3, 30));

        PayData payData3 = PayData.builder()
                .firstBillingDate(LocalDate.of(2021, 5, 31))
                .billingDate(LocalDate.of(2021, 6, 30))
                .payAmount(10000)
                .build();
        assertExpiryDate(payData3, LocalDate.of(2021, 7, 31));
    }

    @Test
    void 이만원이상_납부하면_비례해서_만료일_계산(){
        PayData payData = PayData.builder()
                .billingDate(LocalDate.of(2021, 3, 1))
                .payAmount(20000)
                .build();
        assertExpiryDate(payData, LocalDate.of(2021, 5, 1));

        PayData payData2 = PayData.builder()
                .billingDate(LocalDate.of(2021, 3, 1))
                .payAmount(30000)
                .build();
        assertExpiryDate(payData2, LocalDate.of(2021, 6, 1));
    }


    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate){
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expiryDate =  cal.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate, expiryDate);
    }

}
