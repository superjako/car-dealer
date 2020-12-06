package com.sg.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sg.bean.CarBaseInfo;
import com.sg.bean.Talk;
import com.sg.bean.vo.TalkVo;
import com.sg.exception.BusinessException;

import java.util.List;

/**
 * <p>
 * 评论动态表 服务类
 * </p>
 *
 * @author sunpeng
 * @since 2020-12-06
 */
public interface TalkService extends IService<Talk> {

    /**
     * 发表动态
     *
     * @param talkVo
     * @param userId
     * @throws BusinessException
     */
    void saveTalk(TalkVo talkVo, String userId) throws BusinessException;

    /**
     * @return
     * @throws BusinessException
     */
    IPage<TalkVo> queryTalkContentList(Page<CarBaseInfo> page) throws BusinessException;
}
