package com.sg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bean.CarAttach;
import com.sg.bean.CarShop;
import com.sg.mapper.CarShopMapper;
import com.sg.service.CarAttachService;
import com.sg.service.CarShopService;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    CarAttachService carAttachService;

    @Override
    public void saveInfo(CarShop carShop, String imgs, String userId) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createDate = simpleDateFormat.format(new Date());
        if (Strings.isNullOrEmpty(carShop.getId())) {
            carShop.setCreatedate(createDate);
            this.save(carShop);
        } else {
            //编辑
            carShop.setUpdatedate(createDate);
            this.updateById(carShop);
        }

        List<CarAttach> carAttaches = new ArrayList<>();
        //保存数据到附件信息表
        String[] imgArr = imgs.split(",");
        for (String s : imgArr) {
            CarAttach carAttach = new CarAttach();
            carAttach.setBaseInfoId(carShop.getId());
            carAttach.setCreatedate(createDate);
            carAttach.setCreater("");
            carAttach.setFilepath(s);
            carAttaches.add(carAttach);
        }

        LambdaQueryWrapper<CarAttach> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(CarAttach::getBaseInfoId, carShop.getId());

        //先删除
        carAttachService.remove(lambdaQuery);

        carAttachService.saveBatch(carAttaches);
    }
}
