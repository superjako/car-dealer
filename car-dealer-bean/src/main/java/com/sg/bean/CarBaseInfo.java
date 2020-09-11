package com.sg.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 车辆基本信息表
 * </p>
 *
 * @author sunpeng
 * @since 2020-09-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("TB_CAR_BASE_INFO")
public class CarBaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /**
     * 商品标题
     */
    @TableField("TITLE")
    private String title;

    /**
     * 表显里程
     */
    @TableField("DRIVER_MILEAGE")
    private BigDecimal driverMileage;

    /**
     * 挡位/排量
     */
    @TableField("DISPLACEMENT")
    private String displacement;

    /**
     * 汽车所在地
     */
    @TableField("LOCATION")
    private String location;

    /**
     * 车辆价格
     */
    @TableField("PRICE")
    private BigDecimal price;

    /**
     * 汽车品牌ID
     */
    @TableField("BRAND_ID")
    private String brandId;

    /**
     * 上牌日期
     */
    @TableField("ON_CARD_DATE")
    private LocalDateTime onCardDate;

    /**
     * 有效性 0：无效 1：有效
     */
    @TableField("STATUS")
    private String status;

    /**
     * 创建人
     */
    @TableField("CREATER")
    private String creater;

    /**
     * 创建时间
     */
    @TableField("CREATEDATE")
    private LocalDateTime createdate;

    /**
     * 修改人
     */
    @TableField("UPDATER")
    private String updater;

    /**
     * 修改时间
     */
    @TableField("UPDATEDATE")
    private LocalDateTime updatedate;


}
