package com.ou.utils;

public class OTPUtil {
    public String generateOTP(){
        return String.valueOf((int) (Math.random()*900000)+100000);
    }
}
