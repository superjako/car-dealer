package com.sg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bean.CarAttach;
import com.sg.bean.CarBaseInfo;
import com.sg.bean.CarShop;
import com.sg.bean.vo.CarBaseInfoVo;
import com.sg.bean.vo.CarInfoQueryVo;
import com.sg.bean.vo.CarShopVo;
import com.sg.constant.SystemConstant;
import com.sg.exception.BusinessException;
import com.sg.mapper.CarShopMapper;
import com.sg.redis.RedisUtil;
import com.sg.service.CarAttachService;
import com.sg.service.CarShopService;
import com.sg.util.UUIDUtil;
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

    @Autowired
    CarShopMapper carShopMapper;

    @Autowired
    private RedisUtil redisUtil;

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

    @Override
    public IPage<CarShopVo> selectShopInfoPage(Page<CarShop> page) {
        CarInfoQueryVo record = new CarInfoQueryVo();
        IPage<CarShopVo> pageDto = carShopMapper.selectShopInfoPage(page, record);
        for (int i = 0; i < pageDto.getRecords().size(); i++) {
            String id = pageDto.getRecords().get(i).getId();
            //根据id查询图片信息
            LambdaQueryWrapper<CarAttach> lambdaQuery = Wrappers.lambdaQuery();
            lambdaQuery.eq(CarAttach::getBaseInfoId, id);
            List<CarAttach> carAttachList = carAttachService.list(lambdaQuery);
            pageDto.getRecords().get(i).setShopPictureList(carAttachList);
        }
        return pageDto;
    }

    @Override
    public CarShopVo login(String username, String password) throws Exception {
        LambdaQueryWrapper<CarShop> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(CarShop::getPhone, username);

        List<CarShop> userList = this.list(lambdaQuery);
        if (CollectionUtils.isEmpty(userList)) {
            throw new BusinessException("登录失败：用户名或密码错误");
        }
        CarShop user = userList.get(0);
        if (password.equals(user.getPassword())) {
            throw new BusinessException("登录失败：用户名或密码错误");
        }
        // LOGIN TOKEN
       /* String LOGIN_TOKEN = "";
        if (redisUtil.exists(SystemConstant.CACHE_LOGIN_TOKEN + user.getId())) {
            // 当前登录用户已有有效token
            LOGIN_TOKEN = redisUtil
                    .get(SystemConstant.CACHE_LOGIN_TOKEN + user.getId()).toString();
        } else {
            // 当前登录用户没有有效token，则产生新的token，存放在缓存中，12小时有效时长
            LOGIN_TOKEN = UUIDUtil.generateUUID();
            redisUtil.set(SystemConstant.CACHE_LOGIN_TOKEN + user.getId(),
                    LOGIN_TOKEN, 43200L);
        }*/
        user.setPassword(null);
        CarShopVo userVo = new CarShopVo();
        BeanUtils.copyProperties(user, userVo);
        //userVo.setLoginToken(LOGIN_TOKEN);
        return userVo;
    }
}
