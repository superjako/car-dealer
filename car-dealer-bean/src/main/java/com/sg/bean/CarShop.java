package com.sg.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 店铺表
 * </p>
 *
 * @author sunpeng
 * @since 2020-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("TB_CAR_SHOP")
public class CarShop implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /**
     * 用户id（openid或其他）
     */
    @TableField("USER_ID")
    private String userId;

    /**
     * 用户名
     */
    @TableField("USER_NAME")
    private String userName;

    /**
     * 联系电话
     */
    @TableField("PHONE")
    private String phone;

    /**
     * 店铺名称
     */
    @TableField("SHOP_NAME")
    private String shopName;

    /**
     * 店铺logo
     */
    @TableField("LOGO")
    private String logo;

    /**
     * 所在省
     */
    @TableField("PROVINCE")
    private String province;

    /**
     * 所在市
     */
    @TableField("CITY")
    private String city;

    /**
     * 所在区
     */
    @TableField("AREA")
    private String area;

    /**
     * 详细地址
     */
    @TableField("ADDRESS")
    private String address;

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
