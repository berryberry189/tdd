package com.book.tdd.chap03;

import java.time.LocalDate;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonths = payData.getPayAmount() / 10000;
        if(payData.getFirstBillingDate() != null){
            // 첫 납부일과 납부일의 날짜가 다르면 첫 납부일의 일자를 만료일의 일자로 사용
            LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
            if(payData.getFirstBillingDate().getDayOfMonth() != candidateExp.getDayOfMonth()){
                return candidateExp.withDayOfMonth(payData.getFirstBillingDate().getDayOfMonth());
            }
        }
        return payData.getBillingDate().plusMonths(addedMonths);
    }
}
