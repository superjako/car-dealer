package com.sg.bean.system.vo;

import com.sg.bean.system.SystemUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName SystemUserVo
 * @Description
 * @Author sunpeng
 * @Date 2020/5/7 23:43
 * @Version 1.0
 **/
@Data
public class SystemUserVo extends SystemUser {
    @ApiModelProperty(value="用户登录TOKEN")
    private String loginToken;
}
