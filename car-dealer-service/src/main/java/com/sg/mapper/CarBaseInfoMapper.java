package com.sg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bean.CarBaseInfo;
import com.sg.bean.vo.CarBaseInfoVo;
import com.sg.bean.vo.CarInfoQueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 车辆基本信息表 Mapper 接口
 * </p>
 *
 * @author sunpeng
 * @since 2020-09-11
 */
public interface CarBaseInfoMapper extends BaseMapper<CarBaseInfo> {
    /**
     * 分页查询
     *
     * @param page
     * @param record
     * @return
     */
    IPage<CarBaseInfoVo> selectCarInfoPage(Page<CarBaseInfo> page, @Param("record") CarInfoQueryVo record);
}
