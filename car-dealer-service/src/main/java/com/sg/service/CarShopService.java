package com.sg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sg.bean.CarBaseInfo;
import com.sg.bean.CarShop;

/**
 * <p>
 * 店铺表 服务类
 * </p>
 *
 * @author sunpeng
 * @since 2020-09-27
 */
public interface CarShopService extends IService<CarShop> {
    /**
     * 商铺注册
     *
     * @param carShop
     * @param imgs
     * @param userId
     * @throws Exception
     */
    void saveInfo(CarShop carShop, String imgs, String userId) throws Exception;
}
