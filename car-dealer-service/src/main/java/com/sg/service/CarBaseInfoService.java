package com.sg.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sg.bean.*;
import com.sg.bean.vo.CarBaseInfoVo;
import com.sg.bean.vo.CarInfoQueryVo;
import com.sg.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 车辆基本信息表 服务类
 * </p>
 *
 * @author sunpeng
 * @since 2020-09-11
 */
public interface CarBaseInfoService extends IService<CarBaseInfo> {

    /**
     * 分页查询
     *
     * @param page
     * @param record
     * @return
     */
    IPage<CarBaseInfoVo> selectCarInfoPage(Page<CarBaseInfo> page,   String brandId,
                                           String shopId,
                                           String type,
                                           Integer startPrice,
                                           Integer endPrice,
                                           Integer startFirstPay,
                                           Integer endFirstPay);

    /**
     * 保存车辆信息
     *
     * @param carBaseInfo
     * @param carBody
     * @param carChassis
     * @param carEngine
     * @param carGearbox
     * @param imgs
     * @throws BusinessException
     */
    void saveInfo(CarBaseInfo carBaseInfo, CarBody carBody, CarChassis carChassis, CarEngine carEngine, CarGearbox carGearbox, String imgs) throws Exception;

    /**
     * 新增
     *
     * @param carBaseInfo
     * @param userId
     * @throws Exception
     */
    void saveInfo(CarBaseInfoVo carBaseInfo, String userId) throws BusinessException;

    /**
     * 删除车辆信息
     *
     * @param id
     * @param userId
     */
    void deleteInfo(String id, String userId);

    /**
     * 详情
     *
     * @param id
     * @return
     */
    CarBaseInfoVo detail(String id);
}
