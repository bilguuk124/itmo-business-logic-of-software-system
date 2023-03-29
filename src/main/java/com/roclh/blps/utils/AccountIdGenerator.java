package com.roclh.blps.utils;

public class AccountIdGenerator {
    private static Long Id = 1L;

    public static Long getId(){
        return Id++;
    }

    public static void setId(Long num){
        Id = num;
    }
}
