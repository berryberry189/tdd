package com.book.tdd.chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String str){
        if(str == null || str.isEmpty()) return PasswordStrength.INVALID;
        if(str.length() < 8){
            return PasswordStrength.NORMAL;
        }
        boolean containsNum = meetsContainingNumberCriteria(str);
        if(!containsNum) return PasswordStrength.NORMAL;
        boolean containsUpp = meetsContainingUppercaseCriteria(str);
        if(!containsUpp) return PasswordStrength.NORMAL;

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
