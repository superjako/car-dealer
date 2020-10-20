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
 * 品牌对应车系表
 * </p>
 *
 * @author sunpeng
 * @since 2020-10-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("TB_SERIES")
public class Series implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /**
     * 车系名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 汽车品牌id
     */
    @TableField("BRAND_ID")
    private String brandId;

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
