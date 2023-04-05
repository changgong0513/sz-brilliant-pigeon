package com.ruoyi.szbp.member.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.szbp.member.mapper.MemberUserInfoMapper;
import com.ruoyi.szbp.member.domain.MemberUserInfo;
import com.ruoyi.szbp.member.service.IMemberUserInfoService;

/**
 * 会员信息Service业务层处理
 * 
 * @author changgong0513
 * @date 2023-04-05
 */
@Service
public class MemberUserInfoServiceImpl implements IMemberUserInfoService 
{
    @Autowired
    private MemberUserInfoMapper memberUserInfoMapper;

    /**
     * 查询会员信息
     * 
     * @param id 会员信息主键
     * @return 会员信息
     */
    @Override
    public MemberUserInfo selectMemberUserInfoById(Long id)
    {
        return memberUserInfoMapper.selectMemberUserInfoById(id);
    }

    /**
     * 查询会员信息列表
     * 
     * @param memberUserInfo 会员信息
     * @return 会员信息
     */
    @Override
    public List<MemberUserInfo> selectMemberUserInfoList(MemberUserInfo memberUserInfo)
    {
        return memberUserInfoMapper.selectMemberUserInfoList(memberUserInfo);
    }

    /**
     * 新增会员信息
     * 
     * @param memberUserInfo 会员信息
     * @return 结果
     */
    @Override
    public int insertMemberUserInfo(MemberUserInfo memberUserInfo)
    {
        memberUserInfo.setCreateTime(DateUtils.getNowDate());
        return memberUserInfoMapper.insertMemberUserInfo(memberUserInfo);
    }

    /**
     * 修改会员信息
     * 
     * @param memberUserInfo 会员信息
     * @return 结果
     */
    @Override
    public int updateMemberUserInfo(MemberUserInfo memberUserInfo)
    {
        memberUserInfo.setUpdateTime(DateUtils.getNowDate());
        return memberUserInfoMapper.updateMemberUserInfo(memberUserInfo);
    }

    /**
     * 批量删除会员信息
     * 
     * @param ids 需要删除的会员信息主键
     * @return 结果
     */
    @Override
    public int deleteMemberUserInfoByIds(Long[] ids)
    {
        return memberUserInfoMapper.deleteMemberUserInfoByIds(ids);
    }

    /**
     * 删除会员信息信息
     * 
     * @param id 会员信息主键
     * @return 结果
     */
    @Override
    public int deleteMemberUserInfoById(Long id)
    {
        return memberUserInfoMapper.deleteMemberUserInfoById(id);
    }
}
