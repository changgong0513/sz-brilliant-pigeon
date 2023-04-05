package com.ruoyi.szbp.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 赛鸽管理对象 member_pigeon_info
 * 
 * @author changgong0513
 * @date 2023-04-05
 */
public class MemberPigeonInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 会员编号 */
    @Excel(name = "会员编号")
    private String memberId;

    /** 会员姓名 */
    @Excel(name = "会员姓名")
    private String memberName;

    /** 赛鸽足环 */
    @Excel(name = "赛鸽足环")
    private String toeRing;

    /** 赛鸽羽色 */
    @Excel(name = "赛鸽羽色")
    private String featherColor;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMemberId(String memberId) 
    {
        this.memberId = memberId;
    }

    public String getMemberId() 
    {
        return memberId;
    }
    public void setMemberName(String memberName) 
    {
        this.memberName = memberName;
    }

    public String getMemberName() 
    {
        return memberName;
    }
    public void setToeRing(String toeRing) 
    {
        this.toeRing = toeRing;
    }

    public String getToeRing() 
    {
        return toeRing;
    }
    public void setFeatherColor(String featherColor) 
    {
        this.featherColor = featherColor;
    }

    public String getFeatherColor() 
    {
        return featherColor;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("memberId", getMemberId())
            .append("memberName", getMemberName())
            .append("toeRing", getToeRing())
            .append("featherColor", getFeatherColor())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
