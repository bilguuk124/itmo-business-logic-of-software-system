package com.roclh.mainmodule.utils;

public class AccountIdGenerator {
    private static Long Id = 1L;

    public static Long getId() {
        return Id++;
    }

    public static void setId(Long num) {
        Id = num;
    }
}
