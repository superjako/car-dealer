package com.sg.bean.vo;

import lombok.Data;

@Data
public class CarInfoQueryVo {
    //品牌id
    private String brandId;

    //价格区间（始）
    private Integer startPrice;

    //价格区间（末）
    private Integer endPrice;

    //商铺id
    private String shopId;

    //首付
    private Integer startFirstPay;
    private Integer endFirstPay;

    //汽车类型
    private String type;
}
