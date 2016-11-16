/**
 *
 */
package com.gonghoo.pojo;

import java.util.Date;

/**
 * 活动
 *
 * @author gavinshao
 */
public class Activities {
    private Integer id;
    // 标题
    private String title;
    // 副标题
    private String subTitle;
    // 地点、场所
    private String place;
    // 开始时间
    private Date startDate;
    // 结束时间
    private Date endDate;
    // 报名截止时间
    private Date signUpActiveDate;
    // 联系人
    private String linkPerson;
    // 联系电话
    private String linkPhone;
    // 海报
    private String logo;
    // 海报
    private String logo1;
    // 海报
    private String logo2;
    // 海报
    private String logo3;
    // 登录微信详情页二维码
    private String logo4;

    // 活动网站url
    private String webUrl;
    // 简介
    private String summary;
    // 行程住宿
    private String travelStay;
    // 活动分类
    private Integer categoryId;
    // 活动分类名称
    private String categoryName;
    //活动是否需要投稿  1：需要
    private Integer contribute;
    //收款单位
    private String gatheringUnit;
    //推荐类型(1:热门；2：推荐)
    private Integer recommendType = 0;
    //活动关键字
    private String keyword;
    //报名提供信息
    private String attendField;
    //问卷是否完成创建
    private Boolean complete = true;
    //问卷预览状态（还不能正式发布）
    private Boolean preview = true;
    //省份
    private String province;
    //城市
    private String city;
    //县市
    private String county;
    //是否cms网站使用
    private Boolean cmsUse = false;
    //审核状态
    private Integer auditingStatus = 0;
    //联系人邮箱
    private String email;
    //统计报名人数
    private Integer hadSignUpCount;

    private Integer isWei = 0;//是否微活动
    private String templatePath;
    //人数限制
    private Integer limitPerson = 0;
    //活动收费
    private Integer price;
    //活动是否公开
    private Integer open = 1;
    //二维码图片地址
    private String qrCode;
    //是否需要短信提醒
    private Integer needSmsNotice = 0;
    //短信提前通知时间
    private Integer priorNoticeTime = 0;

    private String smsContent;
    //显示在微信列表首页
    private Integer displayList = 0;
    //报名报名者信息
    private Integer secretSignup = 0;
    private Integer finishedSignup = 0;//终止报名
    private Integer zoneMenuId;//微组织菜单id
    //收入总计
    private Double totalRevenue;
    //提现金额
    private Integer cashWithdrawalStatus;
    //创建人姓名
    private String creatorName;
    //创建人电话
    private String creatorPhone;
    //关联圈id
    private Integer hookupId;
    //文档URL
    private String upfiles;
    //是否需要轮播
    private Integer scolling = 0;
    //轮播图片
    private String scollingLogo;

    //轮播顺序
    private Integer scollingIndex = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Integer getScollingIndex() {
        return scollingIndex;
    }

    public void setScollingIndex(Integer scollingIndex) {
        this.scollingIndex = scollingIndex;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getSignUpActiveDate() {
        return signUpActiveDate;
    }

    public void setSignUpActiveDate(Date signUpActiveDate) {
        this.signUpActiveDate = signUpActiveDate;
    }

    public String getLinkPerson() {
        return linkPerson;
    }

    public void setLinkPerson(String linkPerson) {
        this.linkPerson = linkPerson;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogo1() {
        return logo1;
    }

    public void setLogo1(String logo1) {
        this.logo1 = logo1;
    }

    public String getLogo2() {
        return logo2;
    }

    public void setLogo2(String logo2) {
        this.logo2 = logo2;
    }

    public String getLogo3() {
        return logo3;
    }

    public void setLogo3(String logo3) {
        this.logo3 = logo3;
    }

    public String getLogo4() {
        return logo4;
    }

    public void setLogo4(String logo4) {
        this.logo4 = logo4;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTravelStay() {
        return travelStay;
    }

    public void setTravelStay(String travelStay) {
        this.travelStay = travelStay;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getContribute() {
        return contribute;
    }

    public void setContribute(Integer contribute) {
        this.contribute = contribute;
    }

    public String getGatheringUnit() {
        return gatheringUnit;
    }

    public void setGatheringUnit(String gatheringUnit) {
        this.gatheringUnit = gatheringUnit;
    }

    public Integer getRecommendType() {
        return recommendType;
    }

    public void setRecommendType(Integer recommendType) {
        this.recommendType = recommendType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getAttendField() {
        return attendField;
    }

    public void setAttendField(String attendField) {
        this.attendField = attendField;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public Boolean getPreview() {
        return preview;
    }

    public void setPreview(Boolean preview) {
        this.preview = preview;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Boolean getCmsUse() {
        return cmsUse;
    }

    public void setCmsUse(Boolean cmsUse) {
        this.cmsUse = cmsUse;
    }

    public Integer getAuditingStatus() {
        return auditingStatus;
    }

    public void setAuditingStatus(Integer auditingStatus) {
        this.auditingStatus = auditingStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getHadSignUpCount() {
        return hadSignUpCount;
    }

    public void setHadSignUpCount(Integer hadSignUpCount) {
        this.hadSignUpCount = hadSignUpCount;
    }

    public Integer getIsWei() {
        return isWei;
    }

    public void setIsWei(Integer isWei) {
        this.isWei = isWei;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public Integer getLimitPerson() {
        return limitPerson;
    }

    public void setLimitPerson(Integer limitPerson) {
        this.limitPerson = limitPerson;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Integer getNeedSmsNotice() {
        return needSmsNotice;
    }

    public void setNeedSmsNotice(Integer needSmsNotice) {
        this.needSmsNotice = needSmsNotice;
    }

    public Integer getPriorNoticeTime() {
        return priorNoticeTime;
    }

    public void setPriorNoticeTime(Integer priorNoticeTime) {
        this.priorNoticeTime = priorNoticeTime;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public Integer getDisplayList() {
        return displayList;
    }

    public void setDisplayList(Integer displayList) {
        this.displayList = displayList;
    }

    public Integer getSecretSignup() {
        return secretSignup;
    }

    public void setSecretSignup(Integer secretSignup) {
        this.secretSignup = secretSignup;
    }

    public Integer getFinishedSignup() {
        return finishedSignup;
    }

    public void setFinishedSignup(Integer finishedSignup) {
        this.finishedSignup = finishedSignup;
    }

    public Integer getZoneMenuId() {
        return zoneMenuId;
    }

    public void setZoneMenuId(Integer zoneMenuId) {
        this.zoneMenuId = zoneMenuId;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Integer getCashWithdrawalStatus() {
        return cashWithdrawalStatus;
    }

    public void setCashWithdrawalStatus(Integer cashWithdrawalStatus) {
        this.cashWithdrawalStatus = cashWithdrawalStatus;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreatorPhone() {
        return creatorPhone;
    }

    public void setCreatorPhone(String creatorPhone) {
        this.creatorPhone = creatorPhone;
    }

    public Integer getHookupId() {
        return hookupId;
    }

    public void setHookupId(Integer hookupId) {
        this.hookupId = hookupId;
    }

    public String getUpfiles() {
        return upfiles;
    }

    public void setUpfiles(String upfiles) {
        this.upfiles = upfiles;
    }

    public Integer getScolling() {
        return scolling;
    }

    public void setScolling(Integer scolling) {
        this.scolling = scolling;
    }

    public String getScollingLogo() {
        return scollingLogo;
    }

    public void setScollingLogo(String scollingLogo) {
        this.scollingLogo = scollingLogo;
    }

    @Override
    public String toString() {
        return "Activities{" +
                "title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", place='" + place + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", signUpActiveDate=" + signUpActiveDate +
                ", linkPerson='" + linkPerson + '\'' +
                ", linkPhone='" + linkPhone + '\'' +
                ", logo='" + logo + '\'' +
                ", logo1='" + logo1 + '\'' +
                ", logo2='" + logo2 + '\'' +
                ", logo3='" + logo3 + '\'' +
                ", logo4='" + logo4 + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", summary='" + summary + '\'' +
                ", travelStay='" + travelStay + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", contribute=" + contribute +
                ", gatheringUnit='" + gatheringUnit + '\'' +
                ", recommendType=" + recommendType +
                ", keyword='" + keyword + '\'' +
                ", attendField='" + attendField + '\'' +
                ", complete=" + complete +
                ", preview=" + preview +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", cmsUse=" + cmsUse +
                ", auditingStatus=" + auditingStatus +
                ", email='" + email + '\'' +
                ", hadSignUpCount=" + hadSignUpCount +
                ", isWei=" + isWei +
                ", templatePath='" + templatePath + '\'' +
                ", limitPerson=" + limitPerson +
                ", price=" + price +
                ", open=" + open +
                ", qrCode='" + qrCode + '\'' +
                ", needSmsNotice=" + needSmsNotice +
                ", priorNoticeTime=" + priorNoticeTime +
                ", smsContent='" + smsContent + '\'' +
                ", displayList=" + displayList +
                ", secretSignup=" + secretSignup +
                ", finishedSignup=" + finishedSignup +
                ", zoneMenuId=" + zoneMenuId +
                ", totalRevenue=" + totalRevenue +
                ", cashWithdrawalStatus=" + cashWithdrawalStatus +
                ", creatorName='" + creatorName + '\'' +
                ", creatorPhone='" + creatorPhone + '\'' +
                ", hookupId=" + hookupId +
                ", upfiles='" + upfiles + '\'' +
                ", scolling=" + scolling +
                ", scollingLogo='" + scollingLogo + '\'' +
                ", scollingIndex=" + scollingIndex +
                '}';
    }
}
