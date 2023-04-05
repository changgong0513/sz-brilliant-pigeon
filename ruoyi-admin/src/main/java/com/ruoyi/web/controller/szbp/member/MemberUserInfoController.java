package com.ruoyi.web.controller.szbp.member;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.szbp.member.domain.MemberUserInfo;
import com.ruoyi.szbp.member.service.IMemberUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 会员信息Controller
 * 
 * @author changgong0513
 * @date 2023-04-05
 */
@RestController
@RequestMapping("/member/szbpuser")
public class MemberUserInfoController extends BaseController
{
    @Autowired
    private IMemberUserInfoService memberUserInfoService;

    /**
     * 查询会员信息列表
     */
    @PreAuthorize("@ss.hasPermi('member:szbpuser:list')")
    @GetMapping("/list")
    public TableDataInfo list(MemberUserInfo memberUserInfo)
    {
        startPage();
        List<MemberUserInfo> list = memberUserInfoService.selectMemberUserInfoList(memberUserInfo);
        return getDataTable(list);
    }

    /**
     * 导出会员信息列表
     */
    @PreAuthorize("@ss.hasPermi('member:szbpuser:export')")
    @Log(title = "会员信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MemberUserInfo memberUserInfo)
    {
        List<MemberUserInfo> list = memberUserInfoService.selectMemberUserInfoList(memberUserInfo);
        ExcelUtil<MemberUserInfo> util = new ExcelUtil<MemberUserInfo>(MemberUserInfo.class);
        util.exportExcel(response, list, "会员信息数据");
    }

    /**
     * 获取会员信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('member:szbpuser:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(memberUserInfoService.selectMemberUserInfoById(id));
    }

    /**
     * 新增会员信息
     */
    @PreAuthorize("@ss.hasPermi('member:szbpuser:add')")
    @Log(title = "会员信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MemberUserInfo memberUserInfo)
    {
        return toAjax(memberUserInfoService.insertMemberUserInfo(memberUserInfo));
    }

    /**
     * 修改会员信息
     */
    @PreAuthorize("@ss.hasPermi('member:szbpuser:edit')")
    @Log(title = "会员信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MemberUserInfo memberUserInfo)
    {
        return toAjax(memberUserInfoService.updateMemberUserInfo(memberUserInfo));
    }

    /**
     * 删除会员信息
     */
    @PreAuthorize("@ss.hasPermi('member:szbpuser:remove')")
    @Log(title = "会员信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(memberUserInfoService.deleteMemberUserInfoByIds(ids));
    }
}
