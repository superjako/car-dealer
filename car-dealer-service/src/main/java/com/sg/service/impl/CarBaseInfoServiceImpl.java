package com.sg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bean.*;
import com.sg.bean.vo.CarBaseInfoVo;
import com.sg.bean.vo.CarInfoQueryVo;
import com.sg.bean.vo.CarShopVo;
import com.sg.constant.SystemConstant;
import com.sg.exception.BusinessException;
import com.sg.mapper.CarBaseInfoMapper;
import com.sg.service.*;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    CarShopService carShopService;

    @Override
    public IPage<CarBaseInfoVo> selectCarInfoPage(Page<CarBaseInfo> page, String brandId, String shopId, String type, Integer startPrice, Integer endPrice, Integer startFirstPay, Integer endFirstPay) throws BusinessException {
        CarInfoQueryVo record = new CarInfoQueryVo();
        record.setBrandId(brandId);
        record.setShopId(shopId);
        record.setType(type);
        record.setStartPrice(startPrice);
        record.setEndPrice(endPrice);
        record.setStartFirstPay(startFirstPay);
        record.setEndFirstPay(endFirstPay);
        IPage<CarBaseInfoVo> pageDto = carBaseInfoMapper.selectCarInfoPage(page, record);
        for (int i = 0; i < pageDto.getRecords().size(); i++) {
            String id = pageDto.getRecords().get(i).getId();
            //根据id查询图片信息
            LambdaQueryWrapper<CarAttach> lambdaQuery = Wrappers.lambdaQuery();
            lambdaQuery.eq(CarAttach::getBaseInfoId, id);
            List<CarAttach> carAttachList = carAttachService.list(lambdaQuery);
            pageDto.getRecords().get(i).setCarPictureList(carAttachList);
        }

        //访问量统计
        if (StringUtils.isNotEmpty(shopId)) {
            CarShop carShop = carShopService.getById(shopId);
            if (ObjectUtils.isEmpty(carShop)) {
                throw new BusinessException("店铺不存在！");
            }
            carShop.setViewCount(carShop.getViewCount() + 1);
            carShopService.updateById(carShop);
        }
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
    public void saveInfo(CarBaseInfoVo carBaseInfo, String userId) throws BusinessException {
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

        if (!StringUtils.isBlank(carBaseInfo.getImgs())) {
            List<CarAttach> carAttaches = new ArrayList<>();
            //保存数据到附件信息表
            String[] imgArr = carBaseInfo.getImgs().split(",");
            for (String s : imgArr) {
                CarAttach carAttach = new CarAttach();
                carAttach.setBaseInfoId(carBaseInfo.getId());
                carAttach.setCreatedate(createDate);
                carAttach.setCreater("");
                carAttach.setFilepath(s);
                carAttach.setFiletype("0");
                carAttaches.add(carAttach);
            }

            LambdaQueryWrapper<CarAttach> lambdaQuery = Wrappers.lambdaQuery();
            lambdaQuery.eq(CarAttach::getBaseInfoId, carBaseInfo.getId())
                    .eq(CarAttach::getFiletype, "0");

            //先删除
            carAttachService.remove(lambdaQuery);

            carAttachService.saveBatch(carAttaches);
        }

        if (!StringUtils.isBlank(carBaseInfo.getVideos())) {
            List<CarAttach> carAttaches = new ArrayList<>();
            //保存数据到附件信息表
            String[] imgArr = carBaseInfo.getVideos().split(",");
            for (String s : imgArr) {
                CarAttach carAttach = new CarAttach();
                carAttach.setBaseInfoId(carBaseInfo.getId());
                carAttach.setCreatedate(createDate);
                carAttach.setCreater("");
                carAttach.setFilepath(s);
                carAttach.setFiletype("1");
                carAttaches.add(carAttach);
            }

            LambdaQueryWrapper<CarAttach> lambdaQuery = Wrappers.lambdaQuery();
            lambdaQuery.eq(CarAttach::getBaseInfoId, carBaseInfo.getId())
                    .eq(CarAttach::getFiletype, "1");

            //先删除
            carAttachService.remove(lambdaQuery);

            carAttachService.saveBatch(carAttaches);
        }
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
        lambdaQuery.eq(CarAttach::getBaseInfoId, carBaseInfo.getId())
                .eq(CarAttach::getFiletype, "0");
        List<CarAttach> carAttachList = carAttachService.list(lambdaQuery);
        carBaseInfoVo.setCarPictureList(carAttachList);

        //查询视频信息
        LambdaQueryWrapper<CarAttach> lambdaQuery1 = Wrappers.lambdaQuery();
        lambdaQuery1.eq(CarAttach::getBaseInfoId, carBaseInfo.getId())
                .eq(CarAttach::getFiletype, "1");
        List<CarAttach> carAttachList1 = carAttachService.list(lambdaQuery1);
        carBaseInfoVo.setCarVideoList(carAttachList1);
        return carBaseInfoVo;
    }
}
