package com.sg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bean.CarBaseInfo;
import com.sg.bean.CarShop;
import com.sg.bean.vo.CarBaseInfoVo;
import com.sg.bean.vo.CarInfoQueryVo;
import com.sg.bean.vo.CarSaleInfoVo;
import com.sg.bean.vo.CarShopVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 店铺表 Mapper 接口
 * </p>
 *
 * @author sunpeng
 * @since 2020-09-27
 */
public interface CarShopMapper extends BaseMapper<CarShop> {
    /**
     * 分页查询
     *
     * @param page
     * @param record
     * @return
     */
    IPage<CarShopVo> selectShopInfoPage(Page<CarShop> page, @Param("record") CarInfoQueryVo record);

    /**
     * 根据店铺id查询车辆销售数量
     *
     * @param shopId
     * @return
     */
    List<CarSaleInfoVo> selectCarSaleInfoByShopId(@Param("shopId") String shopId);

    /**
     * 查询商家销量排行榜接口
     *
     * @return
     */
    List<CarShopVo> selectShopSaleRankList();

    /**
     * 查询商家规模排行榜接口
     *
     * @return
     */
    List<CarShopVo> selectShopScaleRankList();
}
