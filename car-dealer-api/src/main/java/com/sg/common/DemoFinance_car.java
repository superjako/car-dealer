//
//  Created by  fred on 2016/10/26.
//  Copyright © 2016年 Alibaba. All rights reserved.
//

package com.sg.common;

import com.alibaba.cloudapi.sdk.constant.SdkConstant;
import com.alibaba.cloudapi.sdk.model.ApiCallback;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;
import java.io.IOException;



import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import javax.net.ssl.*;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class DemoFinance_car {


    static{
        //HTTP Client init
        HttpClientBuilderParams httpParam = new HttpClientBuilderParams();
        httpParam.setAppKey("203867310");
        httpParam.setAppSecret("R0m2tWsfE7OlN0HMqJFKXJJ2FBkSb4NA");
        HttpApiClientFinance_car.getInstance().init(httpParam);


        //HTTPS Client init
        HttpClientBuilderParams httpsParam = new HttpClientBuilderParams();
        httpsParam.setAppKey("");
        httpsParam.setAppSecret("");

        /**
        * HTTPS request use DO_NOT_VERIFY mode only for demo
        * Suggest verify for security
        */
        //httpsParam.setRegistry(getNoVerifyRegistry());

        HttpsApiClientFinance_car.getInstance().init(httpsParam);


    }

    public static void 汽车品牌配置详情HttpTest(){
        HttpApiClientFinance_car.getInstance().汽车品牌配置详情("default" , new ApiCallback() {
            @Override
            public void onFailure(ApiRequest request, Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(ApiRequest request, ApiResponse response) {
                try {
                    System.out.println(getResultString(response));
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void 汽车品牌配置详情HttpSyncTest(){
        ApiResponse response = HttpApiClientFinance_car.getInstance().汽车品牌配置详情SyncMode("default");
        try {
            System.out.println(getResultString(response));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }



    public static void 汽车品牌列表_提速版HttpTest(){
        HttpApiClientFinance_car.getInstance().汽车品牌列表_提速版(new ApiCallback() {
            @Override
            public void onFailure(ApiRequest request, Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(ApiRequest request, ApiResponse response) {
                try {
                    System.out.println(getResultString(response));
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void 汽车品牌列表_提速版HttpSyncTest(){
        ApiResponse response = HttpApiClientFinance_car.getInstance().汽车品牌列表_提速版SyncMode();
        try {
            System.out.println(getResultString(response));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void 汽车品牌列表_提速版HttpsTest(){
        HttpsApiClientFinance_car.getInstance().汽车品牌列表_提速版(new ApiCallback() {
            @Override
            public void onFailure(ApiRequest request, Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(ApiRequest request, ApiResponse response) {
                try {
                    System.out.println(getResultString(response));
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void 汽车品牌列表_提速版HttpsSyncTest(){
        ApiResponse response = HttpsApiClientFinance_car.getInstance().汽车品牌列表_提速版SyncMode();
        try {
            System.out.println(getResultString(response));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public static void 汽车品牌配置详情_提速版HttpTest(){
        HttpApiClientFinance_car.getInstance().汽车品牌配置详情_提速版("default" , new ApiCallback() {
            @Override
            public void onFailure(ApiRequest request, Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(ApiRequest request, ApiResponse response) {
                try {
                    System.out.println(getResultString(response));
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void 汽车品牌配置详情_提速版HttpSyncTest(){
        ApiResponse response = HttpApiClientFinance_car.getInstance().汽车品牌配置详情_提速版SyncMode("default");
        try {
            System.out.println(getResultString(response));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void 汽车品牌配置详情_提速版HttpsTest(){
        HttpsApiClientFinance_car.getInstance().汽车品牌配置详情_提速版("default" , new ApiCallback() {
            @Override
            public void onFailure(ApiRequest request, Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(ApiRequest request, ApiResponse response) {
                try {
                    System.out.println(getResultString(response));
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void 汽车品牌配置详情_提速版HttpsSyncTest(){
        ApiResponse response = HttpsApiClientFinance_car.getInstance().汽车品牌配置详情_提速版SyncMode("default");
        try {
            System.out.println(getResultString(response));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public static void 汽车品牌列表查询HttpTest(){
        HttpApiClientFinance_car.getInstance().汽车品牌列表查询(new ApiCallback() {
            @Override
            public void onFailure(ApiRequest request, Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(ApiRequest request, ApiResponse response) {
                try {
                    System.out.println(getResultString(response));
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void 汽车品牌列表查询HttpSyncTest(){
        ApiResponse response = HttpApiClientFinance_car.getInstance().汽车品牌列表查询SyncMode();
        try {
            System.out.println(getResultString(response));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void 汽车品牌列表查询HttpsTest(){
        HttpsApiClientFinance_car.getInstance().汽车品牌列表查询(new ApiCallback() {
            @Override
            public void onFailure(ApiRequest request, Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(ApiRequest request, ApiResponse response) {
                try {
                    System.out.println(getResultString(response));
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void 汽车品牌列表查询HttpsSyncTest(){
        ApiResponse response = HttpsApiClientFinance_car.getInstance().汽车品牌列表查询SyncMode();
        try {
            System.out.println(getResultString(response));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }



    private static String getResultString(ApiResponse response) throws IOException {
        StringBuilder result = new StringBuilder();
        result.append("Response from backend server").append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        result.append("ResultCode:").append(SdkConstant.CLOUDAPI_LF).append(response.getCode()).append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        if(response.getCode() != 200){
            result.append("Error description:").append(response.getHeaders().get("X-Ca-Error-Message")).append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        }

        result.append("ResultBody:").append(SdkConstant.CLOUDAPI_LF).append(new String(response.getBody() , SdkConstant.CLOUDAPI_ENCODING));

        return result.toString();
    }

    private static Registry<ConnectionSocketFactory> getNoVerifyRegistry() {
            RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.create();
            try {
                registryBuilder.register("http", PlainConnectionSocketFactory.INSTANCE).build();
                registryBuilder.register(
                        "https",
                        new SSLConnectionSocketFactory(new SSLContextBuilder().loadTrustMaterial(
                                KeyStore.getInstance(KeyStore.getDefaultType()), new TrustStrategy() {
                                    @Override
                                    public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                                        return true;
                                    }
                                }).build(),
                                new HostnameVerifier() {
                                    @Override
                                    public boolean verify(String paramString, SSLSession paramSSLSession) {
                                        return true;
                                    }
                                }));

            } catch (Exception e) {
                throw new RuntimeException("HttpClientUtil init failure !", e);
            }
            return registryBuilder.build();
        }


        private static void trustAllHttpsCertificates() throws Exception {
            TrustManager[] trustAllCerts = new TrustManager[1];
            TrustManager tm = new miTM();
            trustAllCerts[0] = tm;
            SSLContext sc = SSLContext
                    .getInstance("SSL");
            sc.init(null, trustAllCerts, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sc
                    .getSocketFactory());
        }

        static class miTM implements TrustManager,
                X509TrustManager {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public boolean isServerTrusted(
                    X509Certificate[] certs) {
                return true;
            }

            public boolean isClientTrusted(
                    X509Certificate[] certs) {
                return true;
            }

            public void checkServerTrusted(
                    X509Certificate[] certs, String authType)
                    throws CertificateException {
                return;
            }

            public void checkClientTrusted(
                    X509Certificate[] certs, String authType)
                    throws CertificateException {
                return;
            }
        }
}
