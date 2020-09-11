package com.sg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sg.bean.*;
import com.sg.exception.BusinessException;

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
    void saveInfo(CarBaseInfo carBaseInfo, CarBody carBody, CarChassis carChassis, CarEngine carEngine, CarGearbox carGearbox, String imgs) throws BusinessException;

}
