package com.book.tdd.chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(PayData payData) {
        int addedMonths = payData.getPayAmount() / 10000;
        if(payData.getFirstBillingDate() != null){
            return expiryDateUsingFirstBillingDate(payData, addedMonths);
        }else{
            return payData.getBillingDate().plusMonths(addedMonths);
        }
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
        // 첫 납부일과 납부일의 날짜가 다르면 첫 납부일의 일자를 만료일의 일자로 사용
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);
        if(!isSameDayOfMonth(payData.getFirstBillingDate(), candidateExp)){
            final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
            final int dayLenOfCandiMon = lastDayOfMonth(candidateExp);
            if(dayLenOfCandiMon < dayOfFirstBilling){
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        }else{
            return candidateExp;
        }
    }

    private boolean isSameDayOfMonth(LocalDate firstBillingDate, LocalDate candidateExp){
        return firstBillingDate.getDayOfMonth() == candidateExp.getDayOfMonth();
    }

    private int lastDayOfMonth(LocalDate candidateExp){
        return YearMonth.from(candidateExp).lengthOfMonth();
    }
}
