package com.sg.bean.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarInfoQueryVo {
    //品牌id
    private String brandId;

    //价格区间（始）
    private BigDecimal startPrice;

    //价格区间（末）
    private BigDecimal endPrice;

    //商铺idco
    private String shopId;
}
