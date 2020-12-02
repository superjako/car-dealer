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

import java.util.List;

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

    /**
     * 查询商家销量排行榜接口
     *
     * @return
     * @throws BusinessException
     */
    List<CarShopVo> selectShopSaleRankList() throws BusinessException;

    /**
     * 查询商家访问量排行榜接口
     *
     * @return
     * @throws BusinessException
     */
    List<CarShop> selectShopViewRankList() throws BusinessException;

    /**
     * 查询商家规模排行榜接口
     *
     * @return
     * @throws BusinessException
     */
    List<CarShopVo> selectShopScaleRankList() throws BusinessException;
}
