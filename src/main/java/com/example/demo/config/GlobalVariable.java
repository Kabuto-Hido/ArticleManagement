package com.example.demo.config;

import java.util.Random;

public class GlobalVariable {
    public static final String DEFAULT_PAGE = "1";
    public static final String DEFAULT_LIMIT_USER = "10";
    public static final String DEFAULT_LIMIT_SEARCH = "20";
    public static final String CSV_TYPE = "text/csv";
    public static final int VN_Price = 50000;
    public enum ORDER_STATUS {
        DONE,
        CANCELED,
        PAYMENT_CONFIRM,
        PENDING,
    }

    public enum PAYMENT_TYPE{
        VN_PAY,PAYPAL
    }
    public static String GetOTP() {
        return String.format("%06d", new Random().nextInt(999999));
    }

}
