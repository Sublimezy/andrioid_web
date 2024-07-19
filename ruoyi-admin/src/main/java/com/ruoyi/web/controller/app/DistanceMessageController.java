package com.ruoyi.web.controller.app;

import com.ruoyi.app.entity.domain.DistanceMessage;
import com.ruoyi.app.service.IDistanceMessageService;
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
 * 消息内容Controller
 * 
 * @author å¼ å²©
 * @date 2024-07-02
 */
@RestController
@RequestMapping("/app/message")
public class DistanceMessageController extends BaseController
{
    @Autowired
    private IDistanceMessageService distanceMessageService;

    /**
     * 查询消息内容列表
     */
    @GetMapping("/list")
    public TableDataInfo list(DistanceMessage distanceMessage)
    {
        startPage();
        List<DistanceMessage> list = distanceMessageService.selectDistanceMessageList(distanceMessage);
        return getDataTable(list);
    }

    /**
     * 导出消息内容列表
     */
    @PreAuthorize("@ss.hasPermi('app:message:export')")
    @Log(title = "消息内容", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DistanceMessage distanceMessage)
    {
        List<DistanceMessage> list = distanceMessageService.selectDistanceMessageList(distanceMessage);
        ExcelUtil<DistanceMessage> util = new ExcelUtil<DistanceMessage>(DistanceMessage.class);
        util.exportExcel(response, list, "消息内容数据");
    }

    /**
     * 获取消息内容详细信息
     */
    @PreAuthorize("@ss.hasPermi('app:message:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(distanceMessageService.selectDistanceMessageById(id));
    }

    /**
     * 新增消息内容
     */
    @PreAuthorize("@ss.hasPermi('app:message:add')")
    @Log(title = "消息内容", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DistanceMessage distanceMessage)
    {
        return toAjax(distanceMessageService.insertDistanceMessage(distanceMessage));
    }

    /**
     * 修改消息内容
     */
    @PreAuthorize("@ss.hasPermi('app:message:edit')")
    @Log(title = "消息内容", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DistanceMessage distanceMessage)
    {
        return toAjax(distanceMessageService.updateDistanceMessage(distanceMessage));
    }

    /**
     * 删除消息内容
     */
    @PreAuthorize("@ss.hasPermi('app:message:remove')")
    @Log(title = "消息内容", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(distanceMessageService.deleteDistanceMessageByIds(ids));
    }
}
