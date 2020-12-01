package com.sg.bean.vo;

import lombok.Data;

@Data
public class CarSaleInfoVo {
    //在售数量
    private Integer onSaleCount;

    //已售数量
    private Integer soldCount;

    //店铺开业年份
    private Integer shopYear;

    //销售状态 1在售2 已售 3下架
    private String saleStatus;

    //车辆数量
    private Integer carCount;
}
