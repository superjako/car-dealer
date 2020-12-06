package com.sg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sg.bean.Content;
import com.sg.exception.BusinessException;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author sunpeng
 * @since 2020-12-06
 */
public interface ContentService extends IService<Content> {
    /**
     * 发布评论
     *
     * @param content
     * @param userId
     * @throws BusinessException
     */
    void saveContent(Content content, String userId) throws BusinessException;
}
