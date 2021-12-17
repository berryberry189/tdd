package com.book.tdd.chap02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 암호 검사기
public class PasswordStrengthMeterTest {
    /**
     * 규칙 1. 길이가 8글자 이상
     *  2. 0부터 9까지의 숫자룰 포힘
     *  3. 대문자 포함
     *
     *  3가지 규칙 모두를 충족하면 암호는 강함
     *  2가지 충족 -> 보통
     *  1가지 충족 함> 약함
     */

    private PasswordStrengthMeter meter = new PasswordStrengthMeter();
    private void assertStrength(String password, PasswordStrength expStr){
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);
    }

    // 모든 규칙을 충족하는 경우
    @Test
    void meetsAllCriteria_Then_Strong(){
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!@Add", PasswordStrength.STRONG);
    }

    // 길이만 8글자 미만이고 나머지 조건은 총족하는 경우
    @Test
    void meetsOtherCriteria_expect_for_Length_Then_Normal(){
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
    }

    // 숫자를 포함하지 않고 나머지 조건은 충족하는 경우
    @Test
    void meetsOtherCriteria_expect_for_number_Then_Normal(){
        assertStrength("ab!@Awefw", PasswordStrength.NORMAL);
    }

}
