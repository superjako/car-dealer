//
//  Created by  fred on 2017/1/12.
//  Copyright © 2016年 Alibaba. All rights reserved.
//

package com.sg.common;

import com.alibaba.cloudapi.sdk.client.ApacheHttpClient;
import com.alibaba.cloudapi.sdk.enums.HttpMethod;
import com.alibaba.cloudapi.sdk.enums.ParamPosition;
import com.alibaba.cloudapi.sdk.enums.Scheme;
import com.alibaba.cloudapi.sdk.model.ApiCallback;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;


public class HttpApiClientFinance_car extends ApacheHttpClient{
    public final static String HOST = "autocars.market.alicloudapi.com";
    static HttpApiClientFinance_car instance = new HttpApiClientFinance_car();
    public static HttpApiClientFinance_car getInstance(){return instance;}

    public void init(HttpClientBuilderParams httpClientBuilderParams){
        httpClientBuilderParams.setScheme(Scheme.HTTP);
        httpClientBuilderParams.setHost(HOST);
        super.init(httpClientBuilderParams);
    }




    public void 汽车品牌配置详情(String id , ApiCallback callback) {
        String path = "/carDetail";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("id" , id , ParamPosition.BODY , true);



        sendAsyncRequest(request , callback);
    }

    public ApiResponse 汽车品牌配置详情SyncMode(String id) {
        String path = "/carDetail";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("id" , id , ParamPosition.BODY , true);



        return sendSyncRequest(request);
    }
    public void 汽车品牌列表_提速版(ApiCallback callback) {
        String path = "/carNewList";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        


        sendAsyncRequest(request , callback);
    }

    public ApiResponse 汽车品牌列表_提速版SyncMode() {
        String path = "/carNewList";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        


        return sendSyncRequest(request);
    }
    public void 汽车品牌配置详情_提速版(String id , ApiCallback callback) {
        String path = "/carNewDetail";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("id" , id , ParamPosition.BODY , true);



        sendAsyncRequest(request , callback);
    }

    public ApiResponse 汽车品牌配置详情_提速版SyncMode(String id) {
        String path = "/carNewDetail";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        request.addParam("id" , id , ParamPosition.BODY , true);



        return sendSyncRequest(request);
    }
    public void 汽车品牌列表查询(ApiCallback callback) {
        String path = "/carList";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        


        sendAsyncRequest(request , callback);
    }

    public ApiResponse 汽车品牌列表查询SyncMode() {
        String path = "/carList";
        ApiRequest request = new ApiRequest(HttpMethod.POST_FORM , path);
        


        return sendSyncRequest(request);
    }

}