package com.wyl.webdemo.entity.prod;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 天校帐号信息表
 * </p>
 *
 * @author wyl123
 * @since 2018-11-02
 */
@TableName("tx_account")
public class TxAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 天校帐号表主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 机构id，对应yunying.org_account.id
     */
    @TableField("org_id")
    private Integer orgId;
    /**
     * 机构number，对应yunying.org_account.number
     */
    @TableField("org_number")
    private Integer orgNumber;
    /**
     * 天校帐号角色，6-机构
     */
    @TableField("user_role")
    private Integer userRole;
    /**
     * 订单编号，对应shoukuan.success_order_info.id 0-没有走支付页
     */
    @TableField("order_id")
    private Integer orderId;
    /**
     * 订单流水号 0-没有走支付页
     */
    @TableField("purchase_id")
    private Long purchaseId;
    /**
     * 会员定价，单位元
     */
    @TableField("order_price")
    private Double orderPrice;
    /**
     * 实付金额，单位元
     */
    @TableField("pay_price")
    private Double payPrice;
    /**
     * 1-专业版,2-企业版,3-旗舰版,4-定制版,5-大众版,6-白金版
     */
    @TableField("vip_level")
    private Integer vipLevel;
    /**
     * 0-未生效 1-生效 2-暂停 3-过期
     */
    private Integer status;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 最新更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 生效时间
     */
    @TableField("start_time")
    private Date startTime;
    /**
     * 截止时间
     */
    @TableField("end_time")
    private Date endTime;
    /**
     * 是否删除 0:正常 1:删除
     */
    private Integer isdel;
    /**
     * 剩余天数
     */
    @TableField("remain_day")
    private Integer remainDay;
    /**
     * 有效天数
     */
    @TableField("total_day")
    private Integer totalDay;
    /**
     * 免费额度
     */
    @TableField("free_amount")
    private Double freeAmount;
    /**
     * 最大校区数
     */
    @TableField("campus_count")
    private Integer campusCount;
    /**
     * 最大子帐号数
     */
    @TableField("cascade_account_count")
    private Integer cascadeAccountCount;
    /**
     * 最大通话分钟数
     */
    @TableField("total_call_time")
    private Integer totalCallTime;
    /**
     * 最大短信数
     */
    @TableField("sms_count")
    private Integer smsCount;
    /**
     * 存储空间，单位G
     */
    @TableField("storage_space")
    private Integer storageSpace;
    /**
     * 1-正式 2-内部 3-测试 4-试用
     */
    @TableField("account_type")
    private Integer accountType;
    /**
     * 在读学员数
     */
    @TableField("student_count")
    private Integer studentCount;
    /**
     * 短信后缀变更网关编码,默认编码6:天校
     */
    @TableField("sms_gate")
    private Integer smsGate;
    /**
     * 网校机构id
     */
    @TableField("online_org_id")
    private Integer onlineOrgId;
    /**
     * 网校机构number
     */
    @TableField("online_org_number")
    private Integer onlineOrgNumber;
    /**
     * 网校 视频 云存储空间 单位G
     */
    @TableField("cloud_storage_space")
    private Integer cloudStorageSpace;
    /**
     * 网校并发数量
     */
    @TableField("concurrent_number")
    private Integer concurrentNumber;
    /**
     * 网校 视频资源播放流量 单位G
     */
    private Integer flowrate;
    @TableField("info_fill_status")
    private Integer infoFillStatus;
    @TableField("init_org_name")
    private String initOrgName;
    @TableField("org_slogan")
    private String orgSlogan;
    @TableField("org_logo")
    private String orgLogo;
    /**
     * 权限模块id，用逗号分隔
     */
    @TableField("module_auth")
    private String moduleAuth;
    /**
     * 客户id
     */
    @TableField("customer_id")
    private Long customerId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 白金升级版在读学员数，用于标识套餐类型
     */
    @TableField("baijin_student_count")
    private Integer baijinStudentCount;
    /**
     * map<String,String>的json key:moduleAuth,value:dataStr.
     */
    @TableField("module_auth_data")
    private String moduleAuthData;
    /**
     * 3.0后版本
     */
    @TableField("version_after_three")
    private Integer versionAfterThree;
    /**
     * 是否同步账号数据
     */
    @TableField("sync_account")
    private Integer syncAccount;
    /**
     * 是否复制内置角色
     */
    @TableField("copy_role")
    private Integer copyRole;
    /**
     * 处理校长权限
     */
    @TableField("master_permission")
    private Integer masterPermission;
    /**
     * 电话是否可见
     */
    @TableField("mobile_visible")
    private Integer mobileVisible;
    @TableField("permission_old_new")
    private Integer permissionOldNew;
    /**
     * 处理员工权限
     */
    @TableField("staff_permission")
    private Integer staffPermission;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getOrgNumber() {
        return orgNumber;
    }

    public void setOrgNumber(Integer orgNumber) {
        this.orgNumber = orgNumber;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Double payPrice) {
        this.payPrice = payPrice;
    }

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Integer getRemainDay() {
        return remainDay;
    }

    public void setRemainDay(Integer remainDay) {
        this.remainDay = remainDay;
    }

    public Integer getTotalDay() {
        return totalDay;
    }

    public void setTotalDay(Integer totalDay) {
        this.totalDay = totalDay;
    }

    public Double getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(Double freeAmount) {
        this.freeAmount = freeAmount;
    }

    public Integer getCampusCount() {
        return campusCount;
    }

    public void setCampusCount(Integer campusCount) {
        this.campusCount = campusCount;
    }

    public Integer getCascadeAccountCount() {
        return cascadeAccountCount;
    }

    public void setCascadeAccountCount(Integer cascadeAccountCount) {
        this.cascadeAccountCount = cascadeAccountCount;
    }

    public Integer getTotalCallTime() {
        return totalCallTime;
    }

    public void setTotalCallTime(Integer totalCallTime) {
        this.totalCallTime = totalCallTime;
    }

    public Integer getSmsCount() {
        return smsCount;
    }

    public void setSmsCount(Integer smsCount) {
        this.smsCount = smsCount;
    }

    public Integer getStorageSpace() {
        return storageSpace;
    }

    public void setStorageSpace(Integer storageSpace) {
        this.storageSpace = storageSpace;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    public Integer getSmsGate() {
        return smsGate;
    }

    public void setSmsGate(Integer smsGate) {
        this.smsGate = smsGate;
    }

    public Integer getOnlineOrgId() {
        return onlineOrgId;
    }

    public void setOnlineOrgId(Integer onlineOrgId) {
        this.onlineOrgId = onlineOrgId;
    }

    public Integer getOnlineOrgNumber() {
        return onlineOrgNumber;
    }

    public void setOnlineOrgNumber(Integer onlineOrgNumber) {
        this.onlineOrgNumber = onlineOrgNumber;
    }

    public Integer getCloudStorageSpace() {
        return cloudStorageSpace;
    }

    public void setCloudStorageSpace(Integer cloudStorageSpace) {
        this.cloudStorageSpace = cloudStorageSpace;
    }

    public Integer getConcurrentNumber() {
        return concurrentNumber;
    }

    public void setConcurrentNumber(Integer concurrentNumber) {
        this.concurrentNumber = concurrentNumber;
    }

    public Integer getFlowrate() {
        return flowrate;
    }

    public void setFlowrate(Integer flowrate) {
        this.flowrate = flowrate;
    }

    public Integer getInfoFillStatus() {
        return infoFillStatus;
    }

    public void setInfoFillStatus(Integer infoFillStatus) {
        this.infoFillStatus = infoFillStatus;
    }

    public String getInitOrgName() {
        return initOrgName;
    }

    public void setInitOrgName(String initOrgName) {
        this.initOrgName = initOrgName;
    }

    public String getOrgSlogan() {
        return orgSlogan;
    }

    public void setOrgSlogan(String orgSlogan) {
        this.orgSlogan = orgSlogan;
    }

    public String getOrgLogo() {
        return orgLogo;
    }

    public void setOrgLogo(String orgLogo) {
        this.orgLogo = orgLogo;
    }

    public String getModuleAuth() {
        return moduleAuth;
    }

    public void setModuleAuth(String moduleAuth) {
        this.moduleAuth = moduleAuth;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getBaijinStudentCount() {
        return baijinStudentCount;
    }

    public void setBaijinStudentCount(Integer baijinStudentCount) {
        this.baijinStudentCount = baijinStudentCount;
    }

    public String getModuleAuthData() {
        return moduleAuthData;
    }

    public void setModuleAuthData(String moduleAuthData) {
        this.moduleAuthData = moduleAuthData;
    }

    public Integer getVersionAfterThree() {
        return versionAfterThree;
    }

    public void setVersionAfterThree(Integer versionAfterThree) {
        this.versionAfterThree = versionAfterThree;
    }

    public Integer getSyncAccount() {
        return syncAccount;
    }

    public void setSyncAccount(Integer syncAccount) {
        this.syncAccount = syncAccount;
    }

    public Integer getCopyRole() {
        return copyRole;
    }

    public void setCopyRole(Integer copyRole) {
        this.copyRole = copyRole;
    }

    public Integer getMasterPermission() {
        return masterPermission;
    }

    public void setMasterPermission(Integer masterPermission) {
        this.masterPermission = masterPermission;
    }

    public Integer getMobileVisible() {
        return mobileVisible;
    }

    public void setMobileVisible(Integer mobileVisible) {
        this.mobileVisible = mobileVisible;
    }

    public Integer getPermissionOldNew() {
        return permissionOldNew;
    }

    public void setPermissionOldNew(Integer permissionOldNew) {
        this.permissionOldNew = permissionOldNew;
    }

    public Integer getStaffPermission() {
        return staffPermission;
    }

    public void setStaffPermission(Integer staffPermission) {
        this.staffPermission = staffPermission;
    }

    @Override
    public String toString() {
        return "TxAccount{" +
        ", id=" + id +
        ", orgId=" + orgId +
        ", orgNumber=" + orgNumber +
        ", userRole=" + userRole +
        ", orderId=" + orderId +
        ", purchaseId=" + purchaseId +
        ", orderPrice=" + orderPrice +
        ", payPrice=" + payPrice +
        ", vipLevel=" + vipLevel +
        ", status=" + status +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", isdel=" + isdel +
        ", remainDay=" + remainDay +
        ", totalDay=" + totalDay +
        ", freeAmount=" + freeAmount +
        ", campusCount=" + campusCount +
        ", cascadeAccountCount=" + cascadeAccountCount +
        ", totalCallTime=" + totalCallTime +
        ", smsCount=" + smsCount +
        ", storageSpace=" + storageSpace +
        ", accountType=" + accountType +
        ", studentCount=" + studentCount +
        ", smsGate=" + smsGate +
        ", onlineOrgId=" + onlineOrgId +
        ", onlineOrgNumber=" + onlineOrgNumber +
        ", cloudStorageSpace=" + cloudStorageSpace +
        ", concurrentNumber=" + concurrentNumber +
        ", flowrate=" + flowrate +
        ", infoFillStatus=" + infoFillStatus +
        ", initOrgName=" + initOrgName +
        ", orgSlogan=" + orgSlogan +
        ", orgLogo=" + orgLogo +
        ", moduleAuth=" + moduleAuth +
        ", customerId=" + customerId +
        ", remark=" + remark +
        ", baijinStudentCount=" + baijinStudentCount +
        ", moduleAuthData=" + moduleAuthData +
        ", versionAfterThree=" + versionAfterThree +
        ", syncAccount=" + syncAccount +
        ", copyRole=" + copyRole +
        ", masterPermission=" + masterPermission +
        ", mobileVisible=" + mobileVisible +
        ", permissionOldNew=" + permissionOldNew +
        ", staffPermission=" + staffPermission +
        "}";
    }
}
