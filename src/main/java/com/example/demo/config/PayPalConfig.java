package com.example.demo.config;

public class PayPalConfig {
    public static final String CLIENTID = "AZ6sHQ6Aw-3jT7ATY0KFd61PWx-TAnDvOYfuiSer-wCWfnG0FTs1g1W1qCOlnSy6FDpZWu4gf9jNv2Wz";
    public static final String CLIENTSECRET = "ELjWAK8gPTEf_z5ZuiT4paVg4hfv3rJm169cAd2vZfGTwf7jfAvlUTL8sR0x6RufULlPPIjNIPfuxauZ";
    public static final String MODE = "sandbox";
    public static final String CURRENCY = "USD";
    public static final String CANCEL_URL = "http://localhost:8081/paypal/URL_PAYPAL_CANCEL";
    public static final String SUCCESS_URL = "http://localhost:8081/paypal/URL_PAYPAL_SUCCESS";

    public enum paypalPaymentIntent {
        SALE, AUTHORIZE, ORDER
    }

    public enum paypalPaymentMethod {
        CREDIT_CARD, PAYPAL
    }
    //    public static Map<String, String> paypalSdkConfig() {
//        Map<String, String> configMap = new HashMap<>();
//        configMap.put("mode", MODE);
//        return configMap;
//    }
//
//    public static OAuthTokenCredential oAuthTokenCredential() {
//        return new OAuthTokenCredential(CLIENTID, CLIENTSECRET, paypalSdkConfig());
//    }
//    public static APIContext apiContext() throws PayPalRESTException {
//        APIContext context = new APIContext(oAuthTokenCredential().getAccessToken());
//        context.setConfigurationMap(paypalSdkConfig());
//        return apiContext();
//    }
}
