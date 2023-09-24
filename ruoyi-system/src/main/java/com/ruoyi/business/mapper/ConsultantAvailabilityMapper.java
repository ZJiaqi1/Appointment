package com.ruoyi.business.mapper;

import com.ruoyi.business.domain.ConsultantAvailability;

import java.util.List;

/**
 * 咨询师单日咨询时段安排Mapper接口
 * 
 * @author Leo
 * @date 2023-09-21
 */
public interface ConsultantAvailabilityMapper 
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
     * 删除咨询师单日咨询时段安排
     * 
     * @param taskId 咨询师单日咨询时段安排主键
     * @return 结果
     */
    public int deleteConsultantAvailabilityByTaskId(String taskId);

    /**
     * 批量删除咨询师单日咨询时段安排
     * 
     * @param taskIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteConsultantAvailabilityByTaskIds(String[] taskIds);
}
