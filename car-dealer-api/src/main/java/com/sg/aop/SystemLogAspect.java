package com.sg.aop;

import com.sg.util.DataUtil;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: SystemLogAspect
 * @Description: 系统操作日志记录切面
 * @author: xz
 * @date 2019/12/16 0016 13:46
 */
@Component
@Aspect
@Order(2)
public class SystemLogAspect {

    private static final Logger LOGGER = Logger.getLogger(SystemLogAspect.class);

   /* @Autowired
    private SystemLogService systemLogService;

    @Autowired
    private UserService userService;

    @Autowired
    private CheckBaseService checkBaseService;*/

    //@AfterReturning(pointcut="execution(public * com.sg.api..* (..)) && @annotation(com.sg.bean.annotation.Log)",returning="returnVal")
    @AfterReturning(pointcut = "execution(public * com.sg.api..* (..))", returning = "returnVal")
    public void afterReturning(JoinPoint joinPoint, Object returnVal) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取header参数中的当前用户ID，获取注解参数中的功能模块和操作类型
        String userId = request.getHeader("userId");
        //登录接口没有userId参数，根据username获取userId
        if (DataUtil.isEmpty(userId)) {
            String username = request.getParameter("username");
            if (DataUtil.isNotEmpty(username)) {
              /*  User user = userService.getUserByUsername(username);
                if(DataUtil.isNotEmpty(user)) userId = user.getTid();*/
            }
        }
       /* //清查单位ID
        String checkCompanyId = request.getParameter("checkCompanyId");
        String checkCompanyName = "";
        if(DataUtil.isNotEmpty(checkCompanyId)){
            checkCompanyName = checkBaseService.queryCheckCompanyById(checkCompanyId);
        }
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Log log = method.getAnnotation(Log.class);
        //保存操作日志
        SystemLog systemLog = new SystemLog();
        systemLog.setUserId(userId);
        systemLog.setFunctionModule(log.functionModule());
        systemLog.setOperateObj(checkCompanyName);
        systemLog.setOperateType(log.operateType());
        systemLog.setCreateBy(userId);
        systemLog.setRemark(checkCompanyId);
        systemLog.setDistrictName(userService.getDistrictByUserId(userId));
        systemLog.setRoleName(userService.getRoleByUserId(userId));
        systemLogService.saveLog(systemLog);*/
    }

}
