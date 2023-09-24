package com.ruoyi.business.service;

import com.ruoyi.business.domain.ConsultantAvailability;

import java.util.List;

/**
 * 咨询师单日咨询时段安排Service接口
 * 
 * @author Leo
 * @date 2023-09-21
 */
public interface IConsultantAvailabilityService 
{
    /**
     * 查询咨询师单日咨询时段安排
     * 
     * @param taskId 咨询师单日咨询时段安排主键
     * @return 咨询师单日咨询时段安排
     */
    public ConsultantAvailability selectConsultantAvailabilityByTaskId(String taskId);

    /**
     * 查询咨询师单日咨询时段安排列表
     * 
     * @param consultantAvailability 咨询师单日咨询时段安排
     * @return 咨询师单日咨询时段安排集合
     */
    public List<ConsultantAvailability> selectConsultantAvailabilityList(ConsultantAvailability consultantAvailability);

    /**
     * 新增咨询师单日咨询时段安排
     * 
     * @param consultantAvailability 咨询师单日咨询时段安排
     * @return 结果
     */
    public int insertConsultantAvailability(ConsultantAvailability consultantAvailability);

    /**
     * 修改咨询师单日咨询时段安排
     * 
     * @param consultantAvailability 咨询师单日咨询时段安排
     * @return 结果
     */
    public int updateConsultantAvailability(ConsultantAvailability consultantAvailability);

    /**
     * 批量删除咨询师单日咨询时段安排
     * 
     * @param taskIds 需要删除的咨询师单日咨询时段安排主键集合
     * @return 结果
     */
    public int deleteConsultantAvailabilityByTaskIds(String[] taskIds);

    /**
     * 删除咨询师单日咨询时段安排信息
     * 
     * @param taskId 咨询师单日咨询时段安排主键
     * @return 结果
     */
    public int deleteConsultantAvailabilityByTaskId(String taskId);
}
