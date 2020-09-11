package com.sg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bean.*;
import com.sg.exception.BusinessException;
import com.sg.mapper.CarBaseInfoMapper;
import com.sg.service.*;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 车辆基本信息表 服务实现类
 * </p>
 *
 * @author sunpeng
 * @since 2020-09-11
 */
@Service
public class CarBaseInfoServiceImpl extends ServiceImpl<CarBaseInfoMapper, CarBaseInfo> implements CarBaseInfoService {
    @Autowired
    CarBodyService carBodyService;

    @Autowired
    CarChassisService carChassisService;

    @Autowired
    CarEngineService carEngineService;

    @Autowired
    CarGearboxService carGearboxService;

    @Autowired
    CarAttachService carAttachService;

    @Override
    public void saveInfo(CarBaseInfo carBaseInfo, CarBody carBody, CarChassis carChassis, CarEngine carEngine, CarGearbox carGearbox, String imgs) throws BusinessException {
        if (Strings.isNullOrEmpty(carBaseInfo.getId())) {
            this.save(carBaseInfo);

            carBodyService.save(carBody);

            carChassisService.save(carChassis);

            carEngineService.save(carEngine);

            carGearboxService.save(carGearbox);
        } else {
            //编辑
        }

        List<CarAttach> carAttaches = new ArrayList<>();
        //保存数据到附件信息表
        String[] imgArr = imgs.split(";");
        for (String s : imgArr) {
            String[] imgCharArry = s.split(",");
            CarAttach carAttach = new CarAttach();
            carAttach.setBaseInfoId(carBaseInfo.getId());
            carAttach.setCreatedate(LocalDateTime.now());
            carAttach.setCreater("");
            carAttach.setFilename(imgCharArry[0]);
            carAttach.setFilepath(imgCharArry[1]);
            carAttaches.add(carAttach);
        }

        LambdaQueryWrapper<CarAttach> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(CarAttach::getBaseInfoId, carBaseInfo.getId());

        //先删除
        carAttachService.remove(lambdaQuery);

        carAttachService.saveBatch(carAttaches);
    }
}
