package com.sg.api;


import com.baomidou.mybatisplus.extension.api.R;
import com.sg.BaseController;
import com.sg.bean.CarBaseInfo;
import com.sg.bean.CarShop;
import com.sg.service.CarShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 店铺表 前端控制器
 * </p>
 *
 * @author sunpeng
 * @since 2020-09-27
 */
@Api(tags = "店铺注册")
@RestController
@RequestMapping("/carShop")
public class CarShopController extends BaseController {
    @Autowired
    CarShopService carShopService;

    @ApiOperation(value = "店铺注册")
    @PostMapping("/saveShopInfo")
    public R saveCarInfo(@ApiParam(value = "车辆信息", required = true) CarShop carShop,
                         @ApiParam(value = "图片id(格式：id,id,id...)", required = true) @RequestParam(name = "imgs") String imgs) throws Exception {
        carShopService.saveInfo(carShop, imgs, "");
        return R.ok("");
    }
}
