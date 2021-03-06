package com.sg.bean;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 车辆附件信息
 * </p>
 *
 * @author sunpeng
 * @since 2020-09-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("TB_CAR_ATTACH")
public class CarAttach implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /**
     * 基本信息id
     */
    @TableField("BASE_INFO_ID")
    private String baseInfoId;

    /**
     * 路径地址
     */
    @TableField("FILEPATH")
    private String filepath;

    /**
     * 模块类型 1：发布车辆 2：注册商家 3：发表动态
     */
    @TableField("MODULE_TYPE")
    private String moduleType;

    /**
     * 文件名
     */
    @TableField("FILENAME")
    private String filename;

    /**
     * 文件类型 0:图片或文件 1：视频
     */
    @TableField("FILETYPE")
    private String filetype;

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
