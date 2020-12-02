package com.sg.api;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.BaseController;
import com.sg.bean.CarBaseInfo;
import com.sg.bean.CarShop;
import com.sg.bean.vo.CarShopVo;
import com.sg.constant.SystemConstant;
import com.sg.exception.BusinessException;
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
@RequestMapping("/api/carShop")
public class CarShopController extends BaseController {
    @Autowired
    CarShopService carShopService;

    @ApiOperation(value = "店铺注册")
    @PostMapping("/saveShopInfo")
    public R saveCarInfo(@ApiParam(value = "店铺信息", required = true) @RequestBody CarShopVo carShop) throws Exception {
        carShopService.saveInfo(carShop, carShop.getImgs(), "");
        return R.ok("");
    }

    @ApiOperation(value = "分页查询", notes = "分页查询接口")
    @GetMapping("/selectPageList")
    public R selectPageList(
            @RequestParam(required = false) Integer pageNum,
            @RequestParam(required = false) Integer pageSize) {
        if (pageNum == null) pageNum = SystemConstant.PAGE_NUM;
        if (pageSize == null) pageSize = SystemConstant.PAGE_SIZE;
        return R.ok(carShopService.selectShopInfoPage(new Page<>(pageNum, pageSize)));
    }

    @ApiOperation(value = "查询店铺车辆销售数量信息", notes = "查询店铺车辆销售数量信息")
    @GetMapping("/selectCarSaleInfoByShopId")
    public R selectCarSaleInfoByShopId(
            @RequestParam(required = false) String shopId) throws BusinessException {
        return R.ok(carShopService.selectCarSaleInfoByShopId(shopId));
    }

    @ApiOperation(value = "查询商家销量排行榜接口", notes = "查询商家销量排行榜接口")
    @GetMapping("/selectShopSaleRankList")
    public R selectShopSaleRankList() throws BusinessException {
        return R.ok(carShopService.selectShopSaleRankList());
    }

    @ApiOperation(value = "查询商家人气排行榜接口", notes = "查询商家人气排行榜接口")
    @GetMapping("/selectShopViewRankList")
    public R selectShopViewRankList() throws BusinessException {
        return R.ok(carShopService.selectShopViewRankList());
    }

    @PostMapping("/login")
    @ApiOperation("登录")
    public R<CarShopVo> login(
            @RequestBody CarShopVo carShop) throws Exception {
        CarShopVo userVo = carShopService.login(carShop.getUserName(), carShop.getPassword());
        return R.ok(userVo);
    }
}
