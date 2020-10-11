package com.sg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bean.CarBaseInfo;
import com.sg.bean.CarShop;
import com.sg.bean.vo.CarBaseInfoVo;
import com.sg.bean.vo.CarInfoQueryVo;
import com.sg.bean.vo.CarShopVo;
import org.apache.ibatis.annotations.Param;

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
}
