package com.ruoyi.web.controller.szbp.member;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.szbp.member.domain.MemberUserPigeonInfo;
import com.ruoyi.szbp.member.service.IMemberPigeonInfoService;
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
import com.ruoyi.szbp.member.domain.MemberPigeonInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 赛鸽管理Controller
 * 
 * @author changgong0513
 * @date 2023-04-05
 */
@RestController
@RequestMapping("/member/pigeonmgr")
public class MemberPigeonInfoController extends BaseController
{
    @Autowired
    private IMemberPigeonInfoService memberPigeonInfoService;

    /**
     * 查询赛鸽管理列表
     */
    @PreAuthorize("@ss.hasPermi('member:pigeonmgr:list')")
    @GetMapping("/list")
    public TableDataInfo list(MemberUserPigeonInfo memberUserPigeonInfo) {
        startPage();
        List<MemberUserPigeonInfo> list = memberPigeonInfoService.selectMemberUserPigeonInfoList(memberUserPigeonInfo);
        return getDataTable(list);
    }

    /**
     * 导出赛鸽管理列表
     */
    @PreAuthorize("@ss.hasPermi('member:pigeonmgr:export')")
    @Log(title = "赛鸽管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MemberPigeonInfo memberPigeonInfo)
    {
        List<MemberPigeonInfo> list = memberPigeonInfoService.selectMemberPigeonInfoList(memberPigeonInfo);
        ExcelUtil<MemberPigeonInfo> util = new ExcelUtil<MemberPigeonInfo>(MemberPigeonInfo.class);
        util.exportExcel(response, list, "赛鸽管理数据");
    }

    /**
     * 获取赛鸽管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('member:pigeonmgr:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(memberPigeonInfoService.selectMemberPigeonInfoById(id));
    }

    /**
     * 新增赛鸽管理
     */
    @PreAuthorize("@ss.hasPermi('member:pigeonmgr:add')")
    @Log(title = "赛鸽管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MemberPigeonInfo memberPigeonInfo)
    {
        return toAjax(memberPigeonInfoService.insertMemberPigeonInfo(memberPigeonInfo));
    }

    /**
     * 修改赛鸽管理
     */
    @PreAuthorize("@ss.hasPermi('member:pigeonmgr:edit')")
    @Log(title = "赛鸽管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MemberPigeonInfo memberPigeonInfo)
    {
        return toAjax(memberPigeonInfoService.updateMemberPigeonInfo(memberPigeonInfo));
    }

    /**
     * 删除赛鸽管理
     */
    @PreAuthorize("@ss.hasPermi('member:pigeonmgr:remove')")
    @Log(title = "赛鸽管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(memberPigeonInfoService.deleteMemberPigeonInfoByIds(ids));
    }
}
