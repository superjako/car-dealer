package com.sg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sg.interceptor.LoginCheckInterceptor;
import com.sg.interceptor.SignatureVerifyInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    //以这种方式将拦截器注入为一个bean，可以防止拦截器中无法注入bean的问题出现
    @Bean
    LoginCheckInterceptor getLoginCheckInterceptor(){
        return new LoginCheckInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    	//注册拦截器，添加拦截路径和排除拦截路径-签名验证
        //registry.addInterceptor(new SignatureVerifyInterceptor()).addPathPatterns("/api/**");

        //注册拦截器，添加拦截路径和排除拦截路径-登录验证
        registry.addInterceptor(getLoginCheckInterceptor()).addPathPatterns("/api/**")
        				.excludePathPatterns("/api/login")
                        .excludePathPatterns("/api/**/exportExcel");
    }

}
