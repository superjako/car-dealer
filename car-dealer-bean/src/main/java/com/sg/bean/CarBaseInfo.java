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

/**
 * <p>
 * 车辆基本信息表
 * </p>
 *
 * @author sunpeng
 * @since 2020-09-21
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
     * 汽车所属商铺ID
     */
    @TableField("SHOP_ID")
    private String shopId;

    /**
     * 商品标题
     */
    @TableField("NAME")
    private String name;

    /**
     * 车辆价格
     */
    @TableField("PRICE")
    private BigDecimal price;

    /**
     * 新车指导价
     */
    @TableField("GUIDE_PRICE")
    private BigDecimal guidePrice;

    /**
     * 首付
     */
    @TableField("FIRST_PAY")
    private BigDecimal firstPay;

    /**
     * 月供
     */
    @TableField("MONTH_PAY")
    private BigDecimal monthPay;

    /**
     * 分期数
     */
    @TableField("STAGE")
    private Integer stage;

    /**
     * 里程
     */
    @TableField("MILE")
    private BigDecimal mile;

    /**
     * 过户次数
     */
    @TableField("TRANSFER_NUM")
    private Integer transferNum;

    /**
     * 汽车类型 1：越野 2：轿车 3：商务 4：皮卡
     */
    @TableField("TYPE")
    private String type;

    /**
     * 销售状态 0:未卖出 1：已卖出
     */
    @TableField("SALE_STATUS")
    private String saleStatus;

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
    private String createdate;

    /**
     * 修改人
     */
    @TableField("UPDATER")
    private String updater;

    /**
     * 修改时间
     */
    @TableField("UPDATEDATE")
    private String updatedate;


}
