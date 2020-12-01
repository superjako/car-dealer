package com.sg.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sg.bean.CarBaseInfo;
import com.sg.bean.CarShop;
import com.sg.bean.vo.CarBaseInfoVo;
import com.sg.bean.vo.CarSaleInfoVo;
import com.sg.bean.vo.CarShopVo;
import com.sg.exception.BusinessException;

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

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    IPage<CarShopVo> selectShopInfoPage(Page<CarShop> page);

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    CarShopVo login(String username, String password) throws Exception;

    /**
     * 根据店铺id查询车辆销售信息
     *
     * @param shopId
     * @return
     * @throws BusinessException
     */
    CarSaleInfoVo selectCarSaleInfoByShopId(String shopId) throws BusinessException;
}
