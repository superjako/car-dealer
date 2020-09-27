package com.sg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bean.CarShop;
import com.sg.mapper.CarShopMapper;
import com.sg.service.CarShopService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 店铺表 服务实现类
 * </p>
 *
 * @author sunpeng
 * @since 2020-09-27
 */
@Service
public class CarShopServiceImpl extends ServiceImpl<CarShopMapper, CarShop> implements CarShopService {

    @Override
    public void saveInfo(CarShop carShop, String imgs, String userId) throws Exception {

    }
}
