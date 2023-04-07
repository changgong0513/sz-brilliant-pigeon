package com.ruoyi.szbp.member.mapper;

import java.util.List;
import com.ruoyi.szbp.member.domain.MemberPigeonInfo;
import com.ruoyi.szbp.member.domain.MemberUserPigeonInfo;

/**
 * 赛鸽管理Mapper接口
 * 
 * @author changgong0513
 * @date 2023-04-05
 */
public interface MemberPigeonInfoMapper 
{
    /**
     * 查询赛鸽管理
     * 
     * @param id 赛鸽管理主键
     * @return 赛鸽管理
     */
    public MemberPigeonInfo selectMemberPigeonInfoById(Long id);

    /**
     * 查询赛鸽管理列表
     * 
     * @param memberPigeonInfo 赛鸽管理
     * @return 赛鸽管理集合
     */
    public List<MemberPigeonInfo> selectMemberPigeonInfoList(MemberPigeonInfo memberPigeonInfo);

    /**
     * 查询用户赛鸽数据列表
     *
     * @param memberUserPigeonInfo 用户赛鸽管理
     * @return 用户赛鸽数据集合
     */
    public List<MemberUserPigeonInfo> selectMemberUserPigeonInfoList(MemberUserPigeonInfo memberUserPigeonInfo);

    /**
     * 新增赛鸽管理
     * 
     * @param memberPigeonInfo 赛鸽管理
     * @return 结果
     */
    public int insertMemberPigeonInfo(MemberPigeonInfo memberPigeonInfo);

    /**
     * 修改赛鸽管理
     * 
     * @param memberPigeonInfo 赛鸽管理
     * @return 结果
     */
    public int updateMemberPigeonInfo(MemberPigeonInfo memberPigeonInfo);

    /**
     * 删除赛鸽管理
     * 
     * @param id 赛鸽管理主键
     * @return 结果
     */
    public int deleteMemberPigeonInfoById(Long id);

    /**
     * 批量删除赛鸽管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMemberPigeonInfoByIds(Long[] ids);
}
