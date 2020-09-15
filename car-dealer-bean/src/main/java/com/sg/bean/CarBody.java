package com.sg.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 车身信息表
 * </p>
 *
 * @author sunpeng
 * @since 2020-09-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("TB_CAR_BODY")
public class CarBody implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /**
     * 车辆基本信息表id
     */
    @TableField("BASE_INFO_ID")
    private String baseInfoId;

    /**
     * 长度
     */
    @TableField("LENGTH")
    private Double length;

    @TableField("WIDTH")
    private Double width;

    /**
     * 高度
     */
    @TableField("HEIGHT")
    private Double height;

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
