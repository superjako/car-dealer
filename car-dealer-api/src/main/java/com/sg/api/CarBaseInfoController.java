package com.sg.api;


import com.baomidou.mybatisplus.extension.api.R;
import com.sg.BaseController;
import com.sg.bean.*;
import com.sg.exception.BusinessException;
import com.sg.service.CarBaseInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 车辆基本信息表 前端控制器
 * </p>
 *
 * @author sunpeng
 * @since 2020-09-11
 */
@RestController
@RequestMapping("/api/carBaseInfo")
public class CarBaseInfoController extends BaseController {

    @Autowired
    CarBaseInfoService carBaseInfoService;

    @ApiOperation(value = "发布车辆信息")
    @PostMapping("/saveCarInfo")
    public R saveCarInfo(@RequestBody CarBaseInfo carBaseInfo,
                         @RequestBody CarBody carBody,
                         @RequestBody CarChassis carChassis,
                         @RequestBody CarEngine carEngine,
                         @RequestBody CarGearbox carGearbox,
                         @RequestParam(name = "imgs") String imgs) throws BusinessException {
        carBaseInfoService.saveInfo(carBaseInfo, carBody, carChassis, carEngine, carGearbox, imgs);
        return R.ok("");
    }
}
