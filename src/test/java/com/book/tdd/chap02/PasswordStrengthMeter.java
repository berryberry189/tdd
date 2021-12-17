package com.book.tdd.chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String str){
        if(str == null || str.isEmpty()) return PasswordStrength.INVALID;
        int metCounts = 0;

        if(str.length() >= 8) metCounts ++;
        if(meetsContainingNumberCriteria(str)) metCounts ++;
        if(meetsContainingUppercaseCriteria(str)) metCounts ++;

        if(metCounts == 1) return PasswordStrength.WEAK;
        if(metCounts == 2) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;
    }

    private boolean meetsContainingNumberCriteria(String str) {
        for(char ch : str.toCharArray()){
            if(ch >= '0' && ch <= '9'){
                return true;
            }
        }
        return false;
    }

    private boolean meetsContainingUppercaseCriteria(String str) {
        boolean containsUpp = false;
        for(char ch : str.toCharArray()){
            if(Character.isUpperCase(ch)){
                containsUpp = true;
                break;
            }
        }
        return containsUpp;
    }

}
