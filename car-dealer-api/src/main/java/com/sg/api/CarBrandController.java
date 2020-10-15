package com.sg.api;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.sg.BaseController;
import com.sg.bean.CarBrand;
import com.sg.constant.SystemConstant;
import com.sg.service.CarBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 汽车品牌表 前端控制器
 * </p>
 *
 * @author sunpeng
 * @since 2020-09-11
 */
@Api(tags = "车辆品牌管理")
@RestController
@RequestMapping("/carBrand")
public class CarBrandController extends BaseController {
    @Autowired
    CarBrandService carBrandService;

    @ApiOperation(value = "查询汽车品牌集合")
    @GetMapping("/listBrand")
    public R ListBrand() {
        LambdaQueryWrapper<CarBrand> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(CarBrand::getFirstLetter).eq(CarBrand::getStatus, SystemConstant.NORMAL);
        List<CarBrand> carBrands = carBrandService.list(queryWrapper);
        return R.ok(carBrands);
    }
}
