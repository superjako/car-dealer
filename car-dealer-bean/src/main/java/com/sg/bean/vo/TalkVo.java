package com.sg.bean.vo;

import com.sg.bean.CarAttach;
import com.sg.bean.Content;
import com.sg.bean.Talk;
import lombok.Data;

import java.util.List;

@Data
public class TalkVo extends Talk {
    //图片字符串，逗号隔开
    private String imgs;

    //动态图片信息
    private List<CarAttach> talkPictureList;

    //评论集合
    List<Content> contentList;
}
