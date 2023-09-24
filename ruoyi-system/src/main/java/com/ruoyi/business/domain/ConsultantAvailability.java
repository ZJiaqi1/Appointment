package com.ruoyi.business.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 咨询师单日咨询时段安排对象 consultant_availability
 * 
 * @author Leo
 * @date 2023-09-21
 */
public class ConsultantAvailability extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务id */
    @Excel(name = "任务id")
    private String taskId;

    /** 咨询师姓名 */
    @Excel(name = "咨询师姓名")
    private String userName;

    /** 咨询师手机号 */
    @Excel(name = "咨询师手机号")
    private String mobile;

    /** 留言 */
    @Excel(name = "留言")
    private String message;

    /** 日期选择 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期选择", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateAvailable;

    /** 可用时间 */
    @Excel(name = "可用时间", width = 30, dateFormat = "HH-mm-ss")
    private String timeAvailable;

    /** 任务状态 */
    @Excel(name = "任务状态")
    private Long taskStatus;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 最后登录IP */
    @Excel(name = "最后登录IP")
    private String loginIp;

    /** 最后登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date loginDate;

    public void setTaskId(String taskId) 
    {
        this.taskId = taskId;
    }

    public String getTaskId() 
    {
        return taskId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setMessage(String message) 
    {
        this.message = message;
    }

    public String getMessage() 
    {
        return message;
    }
    public void setDateAvailable(Date dateAvailable) 
    {
        this.dateAvailable = dateAvailable;
    }

    public Date getDateAvailable() 
    {
        return dateAvailable;
    }
    public void setTimeAvailable(String timeAvailable)
    {
        this.timeAvailable = timeAvailable;
    }

    public String getTimeAvailable()
    {
        return timeAvailable;
    }
    public void setTaskStatus(Long taskStatus) 
    {
        this.taskStatus = taskStatus;
    }

    public Long getTaskStatus() 
    {
        return taskStatus;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setLoginIp(String loginIp) 
    {
        this.loginIp = loginIp;
    }

    public String getLoginIp() 
    {
        return loginIp;
    }
    public void setLoginDate(Date loginDate) 
    {
        this.loginDate = loginDate;
    }

    public Date getLoginDate() 
    {
        return loginDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("taskId", getTaskId())
            .append("userName", getUserName())
            .append("mobile", getMobile())
            .append("message", getMessage())
            .append("dateAvailable", getDateAvailable())
            .append("timeAvailable", getTimeAvailable())
            .append("taskStatus", getTaskStatus())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("loginIp", getLoginIp())
            .append("loginDate", getLoginDate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
