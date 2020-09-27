package com.sg.bean.vo;

import com.sg.bean.CarAttach;
import com.sg.bean.CarBaseInfo;
import lombok.Data;

import java.util.List;

@Data
public class CarBaseInfoVo extends CarBaseInfo {
    //车辆图片信息
    private List<CarAttach> carPictureList;
}
