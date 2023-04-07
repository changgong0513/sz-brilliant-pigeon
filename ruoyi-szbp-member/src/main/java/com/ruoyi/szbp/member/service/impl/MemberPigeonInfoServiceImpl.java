package com.ruoyi.szbp.member.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.szbp.member.domain.MemberUserPigeonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.szbp.member.mapper.MemberPigeonInfoMapper;
import com.ruoyi.szbp.member.domain.MemberPigeonInfo;
import com.ruoyi.szbp.member.service.IMemberPigeonInfoService;

/**
 * 赛鸽管理Service业务层处理
 * 
 * @author changgong0513
 * @date 2023-04-05
 */
@Service
public class MemberPigeonInfoServiceImpl implements IMemberPigeonInfoService 
{
    @Autowired
    private MemberPigeonInfoMapper memberPigeonInfoMapper;

    /**
     * 查询赛鸽管理
     * 
     * @param id 赛鸽管理主键
     * @return 赛鸽管理
     */
    @Override
    public MemberPigeonInfo selectMemberPigeonInfoById(Long id)
    {
        return memberPigeonInfoMapper.selectMemberPigeonInfoById(id);
    }

    /**
     * 查询赛鸽管理列表
     * 
     * @param memberPigeonInfo 赛鸽管理
     * @return 赛鸽管理
     */
    @Override
    public List<MemberPigeonInfo> selectMemberPigeonInfoList(MemberPigeonInfo memberPigeonInfo)
    {
        return memberPigeonInfoMapper.selectMemberPigeonInfoList(memberPigeonInfo);
    }

    /**
     * 查询用户赛鸽数据列表
     *
     * @param memberUserPigeonInfo 用户赛鸽管理
     * @return 用户赛鸽数据集合
     */
    public List<MemberUserPigeonInfo> selectMemberUserPigeonInfoList(MemberUserPigeonInfo memberUserPigeonInfo) {
        return memberPigeonInfoMapper.selectMemberUserPigeonInfoList(memberUserPigeonInfo);
    }

    /**
     * 新增赛鸽管理
     * 
     * @param memberPigeonInfo 赛鸽管理
     * @return 结果
     */
    @Override
    public int insertMemberPigeonInfo(MemberPigeonInfo memberPigeonInfo)
    {
        memberPigeonInfo.setCreateTime(DateUtils.getNowDate());
        return memberPigeonInfoMapper.insertMemberPigeonInfo(memberPigeonInfo);
    }

    /**
     * 修改赛鸽管理
     * 
     * @param memberPigeonInfo 赛鸽管理
     * @return 结果
     */
    @Override
    public int updateMemberPigeonInfo(MemberPigeonInfo memberPigeonInfo)
    {
        memberPigeonInfo.setUpdateTime(DateUtils.getNowDate());
        return memberPigeonInfoMapper.updateMemberPigeonInfo(memberPigeonInfo);
    }

    /**
     * 批量删除赛鸽管理
     * 
     * @param ids 需要删除的赛鸽管理主键
     * @return 结果
     */
    @Override
    public int deleteMemberPigeonInfoByIds(Long[] ids)
    {
        return memberPigeonInfoMapper.deleteMemberPigeonInfoByIds(ids);
    }

    /**
     * 删除赛鸽管理信息
     * 
     * @param id 赛鸽管理主键
     * @return 结果
     */
    @Override
    public int deleteMemberPigeonInfoById(Long id)
    {
        return memberPigeonInfoMapper.deleteMemberPigeonInfoById(id);
    }
}
