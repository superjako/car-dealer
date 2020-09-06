package com.sg.bean.system.vo;

import com.sg.bean.system.SystemMenu;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName SystemMenuVo
 * @Description
 * @Author sunpeng
 * @Date 2020/5/8 0008 下午 3:21
 * @Version 1.0
 **/
@Data
public class SystemMenuVo extends SystemMenu {
    @ApiModelProperty(value = "子权限")
    private List<SystemMenuVo> childList;
}
