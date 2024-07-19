package com.ruoyi.web.controller.app;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.app.entity.dto.UserLoginDTO;
import com.ruoyi.app.entity.vo.UserLoginSuccessVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.app.entity.domain.DistanceUser;
import com.ruoyi.app.service.IDistanceUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户信息Controller
 *
 * @author å¼ å²©
 * @date 2024-06-25
 */
@RestController
@RequestMapping("/app/user")
public class DistanceUserController extends BaseController {
    @Autowired
    private IDistanceUserService distanceUserService;

    /**
     * 查询用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('app:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(DistanceUser distanceUser) {
        startPage();
        List<DistanceUser> list = distanceUserService.selectDistanceUserList(distanceUser);
        return getDataTable(list);
    }

    /**
     * 导出用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('app:user:export')")
    @Log(title = "用户信息", businessType = BusinessType.EXPORT)
    public void export(HttpServletResponse response, DistanceUser distanceUser) {
        List<DistanceUser> list = distanceUserService.selectDistanceUserList(distanceUser);
        ExcelUtil<DistanceUser> util = new ExcelUtil<DistanceUser>(DistanceUser.class);
        util.exportExcel(response, list, "用户信息数据");
    }

    /**
     * 获取用户信息详细信息
     */
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId) {
        return success(distanceUserService.selectDistanceUserByUserId(userId));
    }

    /**
     * 新增用户信息
     */
    @Log(title = "用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DistanceUser distanceUser) {
        return toAjax(distanceUserService.insertDistanceUser(distanceUser));
    }

    /**
     * 修改用户信息
     */
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DistanceUser distanceUser) {
        return toAjax(distanceUserService.updateDistanceUser(distanceUser));
    }

    /**
     * 删除用户信息
     */
    @PreAuthorize("@ss.hasPermi('app:user:remove')")
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds) {
        return toAjax(distanceUserService.deleteDistanceUserByUserIds(userIds));
    }

    @PostMapping("/userLogin")
    public AjaxResult userLogin(@RequestParam Map<String, String> params) {

        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setPropertiesFromMap(params);
        UserLoginSuccessVO userLoginSuccessVO = distanceUserService.login(userLoginDTO);

        return success(userLoginSuccessVO);

    }

}
