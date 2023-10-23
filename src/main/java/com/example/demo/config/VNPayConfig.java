package com.example.demo.config;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class VNPayConfig {
    public static final String IPDEFAULT = "0:0:0:0:0:0:0:1";
    public static final String VERSIONVNPAY = "2.1.0";
    public static final String COMMAND_PAY = "querydr";
    public static final String COMMAND_REFUND = "refund";
    public static final String TMNCODE = "6JPT6DRV";
    public static final String CHECKSUM = "VEBWPXOHSLIPCDEKCZLHDUWRMGGBXKSL";
    public static final String CURRCODE =  "VND";
    public static final String BANKCODE = "NCB";
    public static final String ORDERTYPE = "190004";
    public static final String LOCALEDEFAULT = "vn";
    public static final String RETURNURL = "http://localhost:8081/vnpay/payment-detail";//"https://react02-group06.vercel.app/checkout/notification";
    public static final String VNPAYURL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static final String VNPAYREFUNDURL = "https://sandbox.vnpayment.vn/merchant_webapi/api/transaction";

    public static String Sha256(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(message.getBytes("UTF-8"));

            // converting byte array to Hexadecimal String
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }

            digest = sb.toString();

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            digest = "";
        }
        return digest;
    }

    public static String hmacSHA512(final String key, final String data) {
        try {

            if (key == null || data == null) {
                throw new NullPointerException();
            }
            final Mac hmac512 = Mac.getInstance("HmacSHA512");
            byte[] hmacKeyBytes = key.getBytes();
            final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
            hmac512.init(secretKey);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] result = hmac512.doFinal(dataBytes);
            StringBuilder sb = new StringBuilder(2 * result.length);
            for (byte b : result) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();

        } catch (Exception ex) {
            return "";
        }
    }
    public static String getIpAddress(HttpServletRequest request) {
        String ipAdress;
        try {
            ipAdress = request.getHeader("X-FORWARDED-FOR");
            if (ipAdress == null) {
                ipAdress = request.getRemoteAddr();
            }
        } catch (Exception e) {
            ipAdress = "Invalid IP:" + e.getMessage();
        }
        return ipAdress;
    }

    public static String getRandomNumber(int len) {
        Random rnd = new Random();
        String chars = "0123456789";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }


}
