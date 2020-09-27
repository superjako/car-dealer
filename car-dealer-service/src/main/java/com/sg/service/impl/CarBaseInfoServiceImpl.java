package com.sg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bean.*;
import com.sg.bean.vo.CarBaseInfoVo;
import com.sg.bean.vo.CarInfoQueryVo;
import com.sg.constant.SystemConstant;
import com.sg.mapper.CarBaseInfoMapper;
import com.sg.service.*;
import org.assertj.core.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    CarBaseInfoMapper carBaseInfoMapper;

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
    public IPage<CarBaseInfoVo> selectCarInfoPage(Page<CarBaseInfo> page, CarInfoQueryVo record) {
        IPage<CarBaseInfoVo> pageDto = carBaseInfoMapper.selectCarInfoPage(page, record);
        return pageDto;
    }

    @Override
    public void saveInfo(CarBaseInfo carBaseInfo, CarBody carBody, CarChassis carChassis, CarEngine carEngine, CarGearbox carGearbox, String imgs) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createDate = simpleDateFormat.format(new Date());
        if (Strings.isNullOrEmpty(carBaseInfo.getId())) {
            carBaseInfo.setCreatedate(createDate);
            this.save(carBaseInfo);
            //carBaseInfoMapper.insert(carBaseInfo);

            carBody.setBaseInfoId(carBaseInfo.getId());
            carBody.setCreatedate(createDate);
            carBodyService.save(carBody);

            carChassis.setBaseInfoId(carBaseInfo.getId());
            carChassis.setCreatedate(createDate);
            carChassisService.save(carChassis);

            carEngine.setBaseInfoId(carBaseInfo.getId());
            carEngine.setCreatedate(createDate);
            carEngineService.save(carEngine);

            carGearbox.setBaseInfoId(carBaseInfo.getId());
            carGearbox.setCreatedate(createDate);
            carGearboxService.save(carGearbox);
        } else {
            //编辑
            carBaseInfo.setUpdatedate(createDate);
            this.updateById(carBaseInfo);

            LambdaQueryWrapper<CarBody> lambdaQuery = Wrappers.lambdaQuery();
            lambdaQuery.eq(CarBody::getBaseInfoId, carBaseInfo.getId());
            carBody.setUpdatedate(createDate);
            carBodyService.update(carBody, lambdaQuery);

            LambdaQueryWrapper<CarChassis> carChassisLambdaQueryWrapper = Wrappers.lambdaQuery();
            carChassisLambdaQueryWrapper.eq(CarChassis::getBaseInfoId, carBaseInfo.getId());
            carChassis.setUpdatedate(createDate);
            carChassisService.update(carChassis, carChassisLambdaQueryWrapper);

            LambdaQueryWrapper<CarEngine> carEngineLambdaQueryWrapper = Wrappers.lambdaQuery();
            carEngineLambdaQueryWrapper.eq(CarEngine::getBaseInfoId, carBaseInfo.getId());
            carEngine.setUpdatedate(createDate);
            carEngineService.update(carEngine, carEngineLambdaQueryWrapper);

            LambdaQueryWrapper<CarGearbox> carGearboxLambdaQueryWrapper = Wrappers.lambdaQuery();
            carGearboxLambdaQueryWrapper.eq(CarGearbox::getBaseInfoId, carBaseInfo.getId());
            carGearbox.setUpdatedate(createDate);
            carGearboxService.update(carGearbox, carGearboxLambdaQueryWrapper);
        }

        List<CarAttach> carAttaches = new ArrayList<>();
        //保存数据到附件信息表
        String[] imgArr = imgs.split(",");
        for (String s : imgArr) {
            CarAttach carAttach = new CarAttach();
            carAttach.setBaseInfoId(carBaseInfo.getId());
            carAttach.setCreatedate(createDate);
            carAttach.setCreater("");
            carAttach.setFilepath(s);
            carAttaches.add(carAttach);
        }

        LambdaQueryWrapper<CarAttach> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(CarAttach::getBaseInfoId, carBaseInfo.getId());

        //先删除
        carAttachService.remove(lambdaQuery);

        carAttachService.saveBatch(carAttaches);
    }

    @Override
    public void saveInfo(CarBaseInfo carBaseInfo, String imgs, String userId) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createDate = simpleDateFormat.format(new Date());
        if (Strings.isNullOrEmpty(carBaseInfo.getId())) {
            carBaseInfo.setCreatedate(createDate);
            this.save(carBaseInfo);
        } else {
            //编辑
            carBaseInfo.setUpdatedate(createDate);
            this.updateById(carBaseInfo);
        }

        List<CarAttach> carAttaches = new ArrayList<>();
        //保存数据到附件信息表
        String[] imgArr = imgs.split(",");
        for (String s : imgArr) {
            CarAttach carAttach = new CarAttach();
            carAttach.setBaseInfoId(carBaseInfo.getId());
            carAttach.setCreatedate(createDate);
            carAttach.setCreater("");
            carAttach.setFilepath(s);
            carAttaches.add(carAttach);
        }

        LambdaQueryWrapper<CarAttach> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(CarAttach::getBaseInfoId, carBaseInfo.getId());

        //先删除
        carAttachService.remove(lambdaQuery);

        carAttachService.saveBatch(carAttaches);
    }

    @Override
    public void deleteInfo(String id, String userId) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createDate = simpleDateFormat.format(new Date());

        CarBaseInfo carBaseInfo = new CarBaseInfo();
        carBaseInfo.setId(id);
        carBaseInfo.setUpdatedate(createDate);
        carBaseInfo.setUpdater(userId);
        carBaseInfo.setStatus(String.valueOf(SystemConstant.DELETED));
        this.updateById(carBaseInfo);
    }

    @Override
    public CarBaseInfoVo detail(String id) {
        CarBaseInfoVo carBaseInfoVo = new CarBaseInfoVo();
        CarBaseInfo carBaseInfo = this.getById(id);
        BeanUtils.copyProperties(carBaseInfo, carBaseInfoVo);

        //查询图片信息
        LambdaQueryWrapper<CarAttach> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(CarAttach::getBaseInfoId, carBaseInfo.getId());
        List<CarAttach> carAttachList = carAttachService.list(lambdaQuery);

        carBaseInfoVo.setCarPictureList(carAttachList);
        return carBaseInfoVo;
    }
}
