package com.sg.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sg.bean.RestAPIResult;
import com.sg.constant.SystemConstant;
import com.sg.util.MD5Util;

public class SignatureVerifyInterceptor implements HandlerInterceptor {

	private static final Logger LOGGER = Logger.getLogger(SignatureVerifyInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		/*String deviceType = request.getParameter("deviceType");
		if(StringUtil.isEmpty(deviceType)){
			processResult(response, "设备类型deviceType为必传字段，可选值为：ios,android,pc,wechat");
            return false;
		}

        String signature = request.getParameter("signature");
        if("666666".equals(signature)){
        	//签名666666，用于在swagger调试接口
        	return true;
        }
        String requestURI= request.getRequestURI();
        Map<String, String> paramMap = getParameterMap(request);

        LOGGER.info("===请求："+requestURI+" 入参："+paramMap);

        String correctSignature = getSignature(paramMap);
        if(StringUtil.isEmpty(signature) || !signature.equals(correctSignature)){
        	 processResult(response, "签名signature验证不通过");
             return false;
        }*/

        return true;
    }

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		// TODO Auto-generated method stub
	}

	private void processResult(HttpServletResponse response, String errorMsg) throws Exception {
        response.getOutputStream().write(JSON.toJSONString(new RestAPIResult<>(errorMsg)).getBytes("utf-8"));
        response.getOutputStream().flush();
    }

	@SuppressWarnings("rawtypes")
	private Map<String, String> getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map<?, ?> properties = request.getParameterMap();
        // 返回值Map
        Map<String, String> returnMap = new HashMap<String, String>();
        Iterator<?> entries = properties.entrySet().iterator();
		Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

	private String getSignature(Map<String, String> paramMap){
		if(paramMap.containsKey("signature")){
			paramMap.remove("signature");
		}
		Iterator<String> iter=paramMap.keySet().iterator();
		List<String> keyList=new ArrayList<String>();
		while(iter.hasNext()){
			String key=iter.next();
			keyList.add(key);
		}
		for (int i = 0; i < keyList.size()-1; i++) {
			for (int j = i+1; j < keyList.size(); j++) {
				String a=keyList.get(i);
				String b=keyList.get(j);
				if(a.compareTo(b)>0){
					keyList.set(i, b);
					keyList.set(j, a);
				}
			}
		}
		String sign="";
		for (String str : keyList) {
			sign+=str+"="+paramMap.get(str)+"&";
		}
		//sign+="key="+getDeviceKeyByDeviceType(paramMap.get("deviceType"));
		String result = MD5Util.getMD5String(sign);
		return result;
	}



}
