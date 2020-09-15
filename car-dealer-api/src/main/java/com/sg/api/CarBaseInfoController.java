package com.sg.api;


import com.baomidou.mybatisplus.extension.api.R;
import com.sg.BaseController;
import com.sg.bean.*;
import com.sg.bean.vo.CarInfoVo;
import com.sg.exception.BusinessException;
import com.sg.service.CarBaseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "车辆管理")
@RestController
@RequestMapping("/api/carBaseInfo")
public class CarBaseInfoController extends BaseController {

    @Autowired
    CarBaseInfoService carBaseInfoService;

/*    @ApiOperation(value = "发布车辆信息")
    @PostMapping("/saveCarInfo")
    public R saveCarInfo(@ApiParam(value = "车辆基本信息", required = true) @RequestBody CarBaseInfo carBaseInfo,
                         @ApiParam(value = "车身信息", required = true) @RequestBody CarBody carBody,
                         @ApiParam(value = "底盘转向信息", required = true) @RequestBody CarChassis carChassis,
                         @ApiParam(value = "发动机信息", required = true) @RequestBody CarEngine carEngine,
                         @ApiParam(value = "变速箱信息", required = true) @RequestBody CarGearbox carGearbox,
                         @ApiParam(value = "图片id(格式：id,id,id...)", required = true) @RequestParam(name = "imgs") String imgs) throws BusinessException {
        carBaseInfoService.saveInfo(carBaseInfo, carBody, carChassis, carEngine, carGearbox, imgs);
        return R.ok("");
    }*/

    @ApiOperation(value = "发布车辆信息")
    @PostMapping("/saveCarInfo")
    public R saveCarInfo(@ApiParam(value = "车辆信息", required = true) @RequestBody CarInfoVo carInfoVo,
                         @ApiParam(value = "图片id(格式：id,id,id...)", required = true) @RequestParam(name = "imgs") String imgs) throws Exception {
        carBaseInfoService.saveInfo(carInfoVo.getCarBaseInfo(), carInfoVo.getCarBody(), carInfoVo.getCarChassis(), carInfoVo.getCarEngine(), carInfoVo.getCarGearbox(), imgs);
        return R.ok("");
    }
}
