package com.sg.api;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.sg.BaseController;
import com.sg.bean.CarBrand;
import com.sg.bean.Series;
import com.sg.constant.SystemConstant;
import com.sg.service.CarBrandService;
import com.sg.service.SeriesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/api/carBrand")
public class CarBrandController extends BaseController {
    @Autowired
    CarBrandService carBrandService;

    @Autowired
    SeriesService seriesService;

    @ApiOperation(value = "查询汽车品牌集合")
    @GetMapping("/listBrand")
    public R listBrand() {
        LambdaQueryWrapper<CarBrand> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(CarBrand::getFirstLetter).eq(CarBrand::getStatus, SystemConstant.NORMAL);
        List<CarBrand> carBrands = carBrandService.list(queryWrapper);
        return R.ok(carBrands);
    }

    @ApiOperation(value = "根据汽车品牌查询车系集合")
    @GetMapping("/listSeries")
    public R listSeries(@ApiParam(value = "汽车品牌id", required = true) @RequestParam String brandId) {
        LambdaQueryWrapper<Series> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Series::getName).eq(Series::getBrandId, brandId);
        List<Series> seriesList = seriesService.list(queryWrapper);
        return R.ok(seriesList);
    }
}
