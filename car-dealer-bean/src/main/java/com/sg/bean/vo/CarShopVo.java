package com.sg.bean.vo;

import com.sg.bean.CarAttach;
import com.sg.bean.CarShop;
import lombok.Data;

import java.util.List;

@Data
public class CarShopVo extends CarShop {
    //图片
    private String imgs;

    //店铺图片信息
    private List<CarAttach> shopPictureList;

    private String loginToken;

    //已售数量
    private Integer soldCount;
}
