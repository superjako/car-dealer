package com.sg.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 发动机信息表
 * </p>
 *
 * @author sunpeng
 * @since 2020-09-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("TB_CAR_ENGINE")
public class CarEngine implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId("ID")
    private String id;

    /**
     * 车辆基本信息id
     */
    @TableField("BASE_INFO_ID")
    private String baseInfoId;

    /**
     * 发动机型号
     */
    @TableField("ENGINE_MODEL")
    private String engineModel;

    /**
     * 排量
     */
    @TableField("DISPLACEMENT")
    private Integer displacement;

    /**
     * 进气形式
     */
    @TableField("INTAKE_FORM")
    private String intakeForm;

    /**
     * 气缸数
     */
    @TableField("CYLINDER_NUMBER")
    private Integer cylinderNumber;

    /**
     * 每缸气门数
     */
    @TableField("CYLINDER_VALVE_NUMBER")
    private Integer cylinderValveNumber;

    /**
     * 燃料形式
     */
    @TableField("FUEL_FORM")
    private String fuelForm;

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
