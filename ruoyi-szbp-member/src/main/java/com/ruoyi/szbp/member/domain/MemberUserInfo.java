package com.ruoyi.szbp.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 会员信息对象 member_user_info
 * 
 * @author changgong0513
 * @date 2023-04-05
 */
public class MemberUserInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 用户名 */
    @Excel(name = "用户名")
    private String memberName;

    /** 会员编号 */
    @Excel(name = "会员编号")
    private String memberId;

    /** 电话 */
    @Excel(name = "电话")
    private String memberMobile;

    /** 隶属协会编号 */
    @Excel(name = "隶属协会编号")
    private String affiliationAssociationId;

    /** 隶属协会名称 */
    @Excel(name = "隶属协会名称")
    private String affiliationAssociationName;

    /** 交鸽羽数 */
    @Excel(name = "交鸽羽数")
    private Long featherNumber;

    /** 代理姓名 */
    @Excel(name = "代理姓名")
    private String proxyName;

    /** 代理电话 */
    @Excel(name = "代理电话")
    private String proxyMobile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberMobile() {
        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }

    public String getAffiliationAssociationId() {
        return affiliationAssociationId;
    }

    public void setAffiliationAssociationId(String affiliationAssociationId) {
        this.affiliationAssociationId = affiliationAssociationId;
    }

    public String getAffiliationAssociationName() {
        return affiliationAssociationName;
    }

    public void setAffiliationAssociationName(String affiliationAssociationName) {
        this.affiliationAssociationName = affiliationAssociationName;
    }

    public Long getFeatherNumber() {
        return featherNumber;
    }

    public void setFeatherNumber(Long featherNumber) {
        this.featherNumber = featherNumber;
    }

    public String getProxyName() {
        return proxyName;
    }

    public void setProxyName(String proxyName) {
        this.proxyName = proxyName;
    }

    public String getProxyMobile() {
        return proxyMobile;
    }

    public void setProxyMobile(String proxyMobile) {
        this.proxyMobile = proxyMobile;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userName", getMemberName())
            .append("memberId", getMemberId())
            .append("memberMobile", getMemberMobile())
            .append("affiliationAssociationId", getAffiliationAssociationId())
            .append("affiliationAssociationName", getAffiliationAssociationName())
            .append("featherNumber", getFeatherNumber())
            .append("proxyName", getProxyName())
            .append("proxyMobile", getProxyMobile())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
