package com.ruoyi.szbp.member.service;

import java.util.List;
import com.ruoyi.szbp.member.domain.MemberUserInfo;

/**
 * 会员信息Service接口
 * 
 * @author changgong0513
 * @date 2023-04-05
 */
public interface IMemberUserInfoService 
{
    /**
     * 查询会员信息
     * 
     * @param id 会员信息主键
     * @return 会员信息
     */
    public MemberUserInfo selectMemberUserInfoById(Long id);

    /**
     * 查询会员信息列表
     * 
     * @param memberUserInfo 会员信息
     * @return 会员信息集合
     */
    public List<MemberUserInfo> selectMemberUserInfoList(MemberUserInfo memberUserInfo);

    /**
     * 新增会员信息
     * 
     * @param memberUserInfo 会员信息
     * @return 结果
     */
    public int insertMemberUserInfo(MemberUserInfo memberUserInfo);

    /**
     * 修改会员信息
     * 
     * @param memberUserInfo 会员信息
     * @return 结果
     */
    public int updateMemberUserInfo(MemberUserInfo memberUserInfo);

    /**
     * 批量删除会员信息
     * 
     * @param ids 需要删除的会员信息主键集合
     * @return 结果
     */
    public int deleteMemberUserInfoByIds(Long[] ids);

    /**
     * 删除会员信息信息
     * 
     * @param id 会员信息主键
     * @return 结果
     */
    public int deleteMemberUserInfoById(Long id);
}
