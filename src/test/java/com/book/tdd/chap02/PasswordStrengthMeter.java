package com.book.tdd.chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String str){
        if(str == null || str.isEmpty()) return PasswordStrength.INVALID;
        if(str.length() < 8){
            return PasswordStrength.NORMAL;
        }
        boolean containsNum = false;
        for(char ch :str.toCharArray()){
            if(ch >= '0' && ch <= '9'){
                containsNum = true;
                break;
            }
        }
        if(!containsNum) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

}
