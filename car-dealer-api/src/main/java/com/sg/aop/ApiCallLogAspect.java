package com.sg.aop;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.sg.bean.RestAPIResult;
import com.sg.exception.BusinessException;
import org.springframework.web.multipart.MultipartFile;

@Component
@Aspect
@Order(1)
public class ApiCallLogAspect {

    private static final Logger LOGGER = Logger.getLogger(ApiCallLogAspect.class);

    /**
     * 所有接口在执行之前输出入参
     *
     * @param joinPoint
     * @throws BusinessException
     */
    @Before("execution(public * com.sg.api..* (..))")
    public void doBeforeInService(JoinPoint joinPoint) throws BusinessException {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String requestURI = request.getRequestURI(); //请求的URL
        Map<String, String> paramMap = getParameterMap(request);

        if (CollectionUtils.isEmpty(paramMap)) {
            Object[] logArgs = joinPoint.getArgs();

            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            String[] paramNames = methodSignature.getParameterNames();

            StringBuffer buff = new StringBuffer();
            buff.append("{");
            for (int i = 0; i < paramNames.length; i++) {
                if (logArgs[i] instanceof ServletRequest || logArgs[i] instanceof ServletResponse || logArgs[i] instanceof MultipartFile) {
                    //ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
                    //ServletResponse不能序列化 从入参里排除，否则报异常：java.lang.IllegalStateException: getOutputStream() has already been called for this response
                    continue;
                }

                buff.append(paramNames[i]);
                buff.append("=");
                buff.append(JSONObject.toJSONString(logArgs[i]));
                if (i < paramNames.length - 1) {
                    buff.append(", ");
                }
            }
            buff.append("}");
            LOGGER.info("===请求：" + requestURI + " 入参：" + buff.toString());
        } else {
            LOGGER.info("===请求：" + requestURI + " 入参：" + paramMap);
        }
    }

    @SuppressWarnings("rawtypes")
    /**
     * 所有接口在返回结果之后输出返回结果
     * @param jp
     * @param returnVal
     */
    @AfterReturning(pointcut = "execution(public * com.sg.api..* (..))", returning = "returnVal")
    public void afterReturning(JoinPoint jp, Object returnVal) throws BusinessException {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String requestURI = request.getRequestURI(); //请求的URL

        if (returnVal instanceof RestAPIResult) {
            RestAPIResult result = (RestAPIResult) returnVal;
            LOGGER.info("===请求：" + requestURI + " 结果：" + JSONObject.toJSONString(result));
        }
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

}
