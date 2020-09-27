package com.sg.api;


import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.BaseController;
import com.sg.bean.CarBaseInfo;
import com.sg.bean.vo.CarBaseInfoVo;
import com.sg.bean.vo.CarInfoQueryVo;
import com.sg.constant.SystemConstant;
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

    /**
     * 分页查询数据
     *
     * @param vo :筛选条件实体类对象
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询接口")
    @GetMapping("/selectPageList")
    public R selectPageList(
            @ApiParam("查询条件实体对象") @RequestBody(required = false) CarInfoQueryVo vo) {
        if (vo.getPageNum() == null) vo.setPageNum(SystemConstant.PAGE_NUM);
        if (vo.getPageSize() == null) vo.setPageSize(SystemConstant.PAGE_SIZE);
        return R.ok(carBaseInfoService.selectCarInfoPage(new Page<>(vo.getPageNum(), vo.getPageSize()), vo));
    }

    @ApiOperation(value = "发布和编辑车辆信息")
    @PostMapping("/saveCarInfo")
    public R saveCarInfo(@ApiParam(value = "车辆信息", required = true) @RequestBody CarBaseInfoVo carBaseInfo) throws BusinessException {
        carBaseInfoService.saveInfo(carBaseInfo, "");
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
