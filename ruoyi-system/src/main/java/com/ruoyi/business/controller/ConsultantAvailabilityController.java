package com.ruoyi.business.controller;

import com.ruoyi.business.domain.ConsultantAvailability;
import com.ruoyi.business.service.IConsultantAvailabilityService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 咨询师单日咨询时段安排Controller
 * 
 * @author Leo
 * @date 2023-09-21
 */
@RestController
@RequestMapping("/business/availability")
public class ConsultantAvailabilityController extends BaseController
{
    @Autowired
    private IConsultantAvailabilityService consultantAvailabilityService;

    /**
     * 查询咨询师单日咨询时段安排列表
     */
    @PreAuthorize("@ss.hasPermi('business:availability:list')")
    @GetMapping("/list")
    public TableDataInfo list(ConsultantAvailability consultantAvailability)
    {
        startPage();
        List<ConsultantAvailability> list = consultantAvailabilityService.selectConsultantAvailabilityList(consultantAvailability);
        return getDataTable(list);
    }

    /**
     * 导出咨询师单日咨询时段安排列表
     */
    @PreAuthorize("@ss.hasPermi('business:availability:export')")
    @Log(title = "咨询师单日咨询时段安排", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ConsultantAvailability consultantAvailability)
    {
        List<ConsultantAvailability> list = consultantAvailabilityService.selectConsultantAvailabilityList(consultantAvailability);
        ExcelUtil<ConsultantAvailability> util = new ExcelUtil<ConsultantAvailability>(ConsultantAvailability.class);
        util.exportExcel(response, list, "咨询师单日咨询时段安排数据");
    }

    /**
     * 获取咨询师单日咨询时段安排详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:availability:query')")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") String taskId)
    {
        return success(consultantAvailabilityService.selectConsultantAvailabilityByTaskId(taskId));
    }

    /**
     * 新增咨询师单日咨询时段安排
     */
    @PreAuthorize("@ss.hasPermi('business:availability:add')")
    @Log(title = "咨询师单日咨询时段安排", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ConsultantAvailability consultantAvailability)
    {
        return toAjax(consultantAvailabilityService.insertConsultantAvailability(consultantAvailability));
    }

    /**
     * 修改咨询师单日咨询时段安排
     */
    @PreAuthorize("@ss.hasPermi('business:availability:edit')")
    @Log(title = "咨询师单日咨询时段安排", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ConsultantAvailability consultantAvailability)
    {
        return toAjax(consultantAvailabilityService.updateConsultantAvailability(consultantAvailability));
    }

    /**
     * 删除咨询师单日咨询时段安排
     */
    @PreAuthorize("@ss.hasPermi('business:availability:remove')")
    @Log(title = "咨询师单日咨询时段安排", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable String[] taskIds)
    {
        return toAjax(consultantAvailabilityService.deleteConsultantAvailabilityByTaskIds(taskIds));
    }
}
