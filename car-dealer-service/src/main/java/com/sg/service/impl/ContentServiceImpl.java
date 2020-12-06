package com.sg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bean.Content;
import com.sg.exception.BusinessException;
import com.sg.mapper.ContentMapper;
import com.sg.service.ContentService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author sunpeng
 * @since 2020-12-06
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

    @Override
    public void saveContent(Content content, String userId) throws BusinessException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createDate = simpleDateFormat.format(new Date());
        content.setCreatedate(createDate);
        content.setCreater(userId);
        this.save(content);
    }
}
