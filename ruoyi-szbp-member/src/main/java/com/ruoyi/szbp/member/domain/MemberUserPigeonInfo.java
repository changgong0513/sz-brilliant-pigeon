package com.ruoyi.szbp.member.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class MemberUserPigeonInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 会员编号 */
    @Excel(name = "会员编号")
    private String memberId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String memberName;

    /** 电话 */
    @Excel(name = "电话")
    private String memberMobile;

    /** 隶属协会编号 */
    @Excel(name = "隶属协会编号")
    private Long affiliationAssociationId;

    /** 隶属协会名称 */
    @Excel(name = "隶属协会名称")
    private String affiliationAssociationName;

    /** 代理姓名 */
    @Excel(name = "代理姓名")
    private String proxyName;

    /** 赛鸽足环 */
    @Excel(name = "赛鸽足环")
    private String toeRing;

    /** 赛鸽羽色 */
    @Excel(name = "赛鸽羽色")
    private String featherColor;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberMobile() {
        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }

    public Long getAffiliationAssociationId() {
        return affiliationAssociationId;
    }

    public void setAffiliationAssociationId(Long affiliationAssociationId) {
        this.affiliationAssociationId = affiliationAssociationId;
    }

    public String getAffiliationAssociationName() {
        return affiliationAssociationName;
    }

    public void setAffiliationAssociationName(String affiliationAssociationName) {
        this.affiliationAssociationName = affiliationAssociationName;
    }

    public String getProxyName() {
        return proxyName;
    }

    public void setProxyName(String proxyName) {
        this.proxyName = proxyName;
    }

    public String getToeRing() {
        return toeRing;
    }

    public void setToeRing(String toeRing) {
        this.toeRing = toeRing;
    }

    public String getFeatherColor() {
        return featherColor;
    }

    public void setFeatherColor(String featherColor) {
        this.featherColor = featherColor;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("memberId", getMemberId())
                .append("memberName", getMemberName())
                .append("memberMobile", getMemberMobile())
                .append("affiliationAssociationId", getAffiliationAssociationId())
                .append("affiliationAssociationName", getAffiliationAssociationName())
                .append("proxyName", getProxyName())
                .append("toeRing", getToeRing())
                .append("featherColor", getFeatherColor())
                .toString();
    }
}
