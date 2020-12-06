package com.sg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sg.bean.CarAttach;
import com.sg.bean.CarBaseInfo;
import com.sg.bean.Content;
import com.sg.bean.Talk;
import com.sg.bean.vo.TalkVo;
import com.sg.constant.SystemConstant;
import com.sg.exception.BusinessException;
import com.sg.mapper.TalkMapper;
import com.sg.service.CarAttachService;
import com.sg.service.ContentService;
import com.sg.service.TalkService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 评论动态表 服务实现类
 * </p>
 *
 * @author sunpeng
 * @since 2020-12-06
 */
@Service
public class TalkServiceImpl extends ServiceImpl<TalkMapper, Talk> implements TalkService {
    @Autowired
    CarAttachService carAttachService;

    @Autowired
    TalkMapper talkMapper;

    @Autowired
    ContentService contentService;

    @Override
    public void saveTalk(TalkVo talkVo, String userId) throws BusinessException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createDate = simpleDateFormat.format(new Date());
        if (!StringUtils.isBlank(talkVo.getImgs())) {
            List<CarAttach> carAttaches = new ArrayList<>();
            //保存数据到附件信息表
            String[] imgArr = talkVo.getImgs().split(",");
            for (String s : imgArr) {
                CarAttach carAttach = new CarAttach();
                carAttach.setBaseInfoId(talkVo.getId());
                carAttach.setCreatedate(createDate);
                carAttach.setCreater("");
                carAttach.setFilepath(s);
                carAttach.setFiletype("0");
                carAttach.setModuleType(SystemConstant.SAVE_TALK);
                carAttaches.add(carAttach);
            }

            LambdaQueryWrapper<CarAttach> lambdaQuery = Wrappers.lambdaQuery();
            lambdaQuery.eq(CarAttach::getBaseInfoId, talkVo.getId())
                    .eq(CarAttach::getFiletype, "0");

            //先删除
            carAttachService.remove(lambdaQuery);

            carAttachService.saveBatch(carAttaches);
        }
    }

    @Override
    public IPage<TalkVo> queryTalkContentList(Page<CarBaseInfo> page) throws BusinessException {
        IPage<TalkVo> pageDto = talkMapper.queryTalkContentList(page);
        //查询动态的图片信息
        for (int i = 0; i < pageDto.getRecords().size(); i++) {
            String id = pageDto.getRecords().get(i).getId();
            //根据id查询图片信息
            LambdaQueryWrapper<CarAttach> lambdaQuery = Wrappers.lambdaQuery();
            lambdaQuery.eq(CarAttach::getBaseInfoId, id);
            List<CarAttach> carAttachList = carAttachService.list(lambdaQuery);
            pageDto.getRecords().get(i).setTalkPictureList(carAttachList);

            //查询动态的评论信息
            LambdaQueryWrapper<Content> contentLambdaQueryWrapper = Wrappers.lambdaQuery();
            contentLambdaQueryWrapper.eq(Content::getTalkId, id);
            List<Content> contentList = contentService.list(contentLambdaQueryWrapper);
            pageDto.getRecords().get(i).setContentList(contentList);
        }
        return pageDto;
    }
}
