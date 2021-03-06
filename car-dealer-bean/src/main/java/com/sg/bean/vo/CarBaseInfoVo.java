package com.sg.bean.vo;

import com.sg.bean.CarAttach;
import com.sg.bean.CarBaseInfo;
import lombok.Data;

import java.util.List;

@Data
public class CarBaseInfoVo extends CarBaseInfo {
    //车辆图片信息
    private List<CarAttach> carPictureList;

    //车辆视频信息
    private  List<CarAttach> carVideoList;

    //图片字符串，逗号隔开
    private String imgs;

    //视频字符串，逗号隔开
    private String videos;
}
