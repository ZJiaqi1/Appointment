package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.ConsultantAvailability;
import com.ruoyi.business.mapper.ConsultantAvailabilityMapper;
import com.ruoyi.business.service.IConsultantAvailabilityService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 咨询师单日咨询时段安排Service业务层处理
 * 
 * @author Leo
 * @date 2023-09-21
 */
@Service
public class ConsultantAvailabilityServiceImpl implements IConsultantAvailabilityService 
{
    @Autowired
    private ConsultantAvailabilityMapper consultantAvailabilityMapper;

    /**
     * 查询咨询师单日咨询时段安排
     * 
     * @param taskId 咨询师单日咨询时段安排主键
     * @return 咨询师单日咨询时段安排
     */
    @Override
    public ConsultantAvailability selectConsultantAvailabilityByTaskId(String taskId)
    {
        return consultantAvailabilityMapper.selectConsultantAvailabilityByTaskId(taskId);
    }

    /**
     * 查询咨询师单日咨询时段安排列表
     * 
     * @param consultantAvailability 咨询师单日咨询时段安排
     * @return 咨询师单日咨询时段安排
     */
    @Override
    public List<ConsultantAvailability> selectConsultantAvailabilityList(ConsultantAvailability consultantAvailability)
    {
        return consultantAvailabilityMapper.selectConsultantAvailabilityList(consultantAvailability);
    }

    /**
     * 新增咨询师单日咨询时段安排
     * 
     * @param consultantAvailability 咨询师单日咨询时段安排
     * @return 结果
     */
    @Override
    public int insertConsultantAvailability(ConsultantAvailability consultantAvailability)
    {
        consultantAvailability.setTaskId(String.valueOf(UUID.randomUUID()));
        consultantAvailability.setCreateTime(DateUtils.getNowDate());
        return consultantAvailabilityMapper.insertConsultantAvailability(consultantAvailability);
    }

    /**
     * 修改咨询师单日咨询时段安排
     * 
     * @param consultantAvailability 咨询师单日咨询时段安排
     * @return 结果
     */
    @Override
    public int updateConsultantAvailability(ConsultantAvailability consultantAvailability)
    {
        consultantAvailability.setUpdateTime(DateUtils.getNowDate());
        return consultantAvailabilityMapper.updateConsultantAvailability(consultantAvailability);
    }

    /**
     * 批量删除咨询师单日咨询时段安排
     * 
     * @param taskIds 需要删除的咨询师单日咨询时段安排主键
     * @return 结果
     */
    @Override
    public int deleteConsultantAvailabilityByTaskIds(String[] taskIds)
    {
        return consultantAvailabilityMapper.deleteConsultantAvailabilityByTaskIds(taskIds);
    }

    /**
     * 删除咨询师单日咨询时段安排信息
     * 
     * @param taskId 咨询师单日咨询时段安排主键
     * @return 结果
     */
    @Override
    public int deleteConsultantAvailabilityByTaskId(String taskId)
    {
        return consultantAvailabilityMapper.deleteConsultantAvailabilityByTaskId(taskId);
    }
}
