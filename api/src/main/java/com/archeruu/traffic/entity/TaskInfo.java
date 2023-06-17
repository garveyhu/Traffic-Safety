package com.archeruu.traffic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 任务信息表
 * @TableName task_info
 */
@TableName(value ="task_info")
@Data
public class TaskInfo implements Serializable {
    /**
     * 标记点id
     */
    @TableId
    private String id;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 标记点地址
     */
    private String markerAddress;

    /**
     * 任务名字
     */
    private String taskName;

    /**
     * 任务开始时间
     */
    private Long startTime;

    /**
     * 任务结束时间
     */
    private Long endTime;

    /**
     * 任务详情
     */
    private String taskDetails;

    /**
     * 创建人名
     */
    private String creatorName;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}