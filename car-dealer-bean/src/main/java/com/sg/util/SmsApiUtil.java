package com.sg.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 云片网短信API工具类
 */
public class SmsApiUtil {

    //查账户信息
    private static String URI_GET_USER_INFO = "https://sms.yunpian.com/v2/user/get.json";

    //单条发送
    private static String URI_SEND_SMS = "https://sms.yunpian.com/v2/sms/single_send.json";

    //语音验证码
    private static String URI_SEND_VOICE = "https://voice.yunpian.com/v2/voice/send.json";

    private static String apikey = "0ed5cfea5c34e5567510f99235aa1142";

    /**
     * 查账户信息
     *
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    public static String getUserInfo() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        return post(URI_GET_USER_INFO, params);
    }

    /**
     * 单条发送
     *
     * @param text   短信文本
     * @param mobile 手机号码
     * @return
     * @throws IOException
     */
    public static String sendSms(String text, String mobile) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("text", text);
        params.put("mobile", mobile);
        return post(URI_SEND_SMS, params);
    }

    /**
     * 发送语音验证码
     *
     * @param mobile 手机号码
     * @param code   验证码
     * @return
     */
    public static String sendVoice(String mobile, String code) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("mobile", mobile);
        params.put("code", code);
        return post(URI_SEND_VOICE, params);
    }

    /**
     * 基于HttpClients的通用POST方法
     *
     * @param url       请求URL
     * @param paramsMap 请求参数
     * @return
     */
    private static String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseText;
    }

}
