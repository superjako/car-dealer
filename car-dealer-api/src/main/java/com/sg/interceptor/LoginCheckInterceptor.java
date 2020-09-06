package com.sg.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.sg.bean.RestAPIResult;
import com.sg.constant.SystemConstant;
import com.sg.redis.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginCheckInterceptor implements HandlerInterceptor {

	@Autowired
	private RedisUtil redisUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String token = request.getHeader("token");
		String userId = request.getHeader("userId");

		//开发调试时开个口子，当userId传1时，允许通过
		//开始
		if(StringUtils.equals(userId, "1")){
			return true;
		}
		//结束

		if (StringUtils.isEmpty(token) || StringUtils.isEmpty(userId)) {
			processResult(response);
			return false;
		}

		String tokenCache = redisUtil.get(SystemConstant.CACHE_LOGIN_TOKEN + userId) == null ? ""
				: redisUtil.get(SystemConstant.CACHE_LOGIN_TOKEN + userId).toString();
		if (!token.equals(tokenCache)) {
			processResult(response);
			return false;
		}
		return true;
	}

	private void processResult(HttpServletResponse response) throws Exception {
		RestAPIResult rest = new RestAPIResult("token验证不通过");
		rest.setRespCode(3);
		response.getOutputStream().write(JSON.toJSONString(rest).getBytes("utf-8"));
		response.getOutputStream().flush();
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

}
