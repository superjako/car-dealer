package com.sg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sg.bean.CarBaseInfo;
import com.sg.bean.Talk;
import com.sg.bean.vo.CarBaseInfoVo;
import com.sg.bean.vo.TalkVo;

/**
 * <p>
 * 评论动态表 Mapper 接口
 * </p>
 *
 * @author sunpeng
 * @since 2020-12-06
 */
public interface TalkMapper extends BaseMapper<Talk> {
    /**
     *
     * @param page
     * @return
     */
    IPage<TalkVo> queryTalkContentList(Page<CarBaseInfo> page);
}
