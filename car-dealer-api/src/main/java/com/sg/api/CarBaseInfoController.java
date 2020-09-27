package com.sg.api;


import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.BaseController;
import com.sg.bean.CarBaseInfo;
import com.sg.bean.vo.CarBaseInfoVo;
import com.sg.bean.vo.CarInfoQueryVo;
import com.sg.constant.SystemConstant;
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

    /**
     * 分页查询数据
     *
     * @param vo       :筛选条件实体类对象
     * @param pageNum  :分页页码
     * @param pageSize :每页查询数量
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询接口")
    @PostMapping("/selectPageList")
    public R selectPageList(
            @ApiParam("查询条件实体对象") @RequestBody CarInfoQueryVo vo,
            @ApiParam("页码") @RequestParam(required = false) Integer pageNum,
            @ApiParam("条数") @RequestParam(required = false) Integer pageSize) {
        pageNum = pageNum == null ? SystemConstant.PAGE_NUM : pageNum;
        pageSize = pageSize == null ? SystemConstant.PAGE_SIZE : pageSize;
        return R.ok(carBaseInfoService.selectCarInfoPage(new Page<>(pageNum, pageSize), vo));
    }

    @ApiOperation(value = "发布和编辑车辆信息")
    @PostMapping("/saveCarInfo")
    public R saveCarInfo(@ApiParam(value = "车辆信息", required = true) @RequestBody CarBaseInfo carBaseInfo,
                         @ApiParam(value = "图片id(格式：id,id,id...)", required = true) @RequestParam(name = "imgs") String imgs) throws Exception {
        carBaseInfoService.saveInfo(carBaseInfo, imgs, "");
        return R.ok("");
    }

    @ApiOperation(value = "删除车辆信息")
    @PostMapping("/deleteInfo")
    public R deleteInfo(@ApiParam(value = "车辆信息id", required = true) @RequestParam String id) throws Exception {
        carBaseInfoService.deleteInfo(id, "");
        return R.ok("");
    }

    @ApiOperation(value = "车辆详情信息")
    @PostMapping("/detail")
    public R detail(@ApiParam(value = "车辆信息id", required = true) @RequestParam String id) throws Exception {
        CarBaseInfoVo detail = carBaseInfoService.detail(id);
        return R.ok(detail);
    }
}
