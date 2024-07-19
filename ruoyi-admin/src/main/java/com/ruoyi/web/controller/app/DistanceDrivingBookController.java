package com.ruoyi.web.controller.app;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.app.entity.domain.DistanceDrivingBook;
import com.ruoyi.app.entity.dto.DrivingBookRequestDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;

import com.ruoyi.app.service.IDistanceDrivingBookService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 驾考题目Controller
 *
 * @author å¼ å²©
 * @date 2024-06-29
 */
@RestController
@RequestMapping("/app/book")
public class DistanceDrivingBookController extends BaseController
{
    @Autowired
    private IDistanceDrivingBookService distanceDrivingBookService;

    /**
     * 查询驾考题目列表
     */
    @GetMapping("/list")
    public TableDataInfo list(DistanceDrivingBook distanceDrivingBook)
    {
        startPage();
        List<DistanceDrivingBook> list = distanceDrivingBookService.selectDistanceDrivingBookList(distanceDrivingBook);
        return getDataTable(list);
    }

    /**
     * 导出驾考题目列表
     */
    @Log(title = "驾考题目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DistanceDrivingBook distanceDrivingBook)
    {
        List<DistanceDrivingBook> list = distanceDrivingBookService.selectDistanceDrivingBookList(distanceDrivingBook);
        ExcelUtil<DistanceDrivingBook> util = new ExcelUtil<DistanceDrivingBook>(DistanceDrivingBook.class);
        util.exportExcel(response, list, "驾考题目数据");
    }

    /**
     * 获取驾考题目详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(distanceDrivingBookService.selectDistanceDrivingBookById(id));
    }

    /**
     * 新增驾考题目
     */
    @Log(title = "驾考题目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DrivingBookRequestDTO drivingBookRequestDTO)
    {
        return toAjax(distanceDrivingBookService.insertDistanceDrivingBook(drivingBookRequestDTO));
    }

    /**
     * 修改驾考题目
     */
    @Log(title = "驾考题目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DistanceDrivingBook distanceDrivingBook)
    {
        return toAjax(distanceDrivingBookService.updateDistanceDrivingBook(distanceDrivingBook));
    }

    /**
     * 删除驾考题目
     */
    @Log(title = "驾考题目", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(distanceDrivingBookService.deleteDistanceDrivingBookByIds(ids));
    }
}
