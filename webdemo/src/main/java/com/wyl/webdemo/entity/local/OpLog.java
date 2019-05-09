package com.wyl.webdemo.entity.local;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wyl123
 * @since 2018-12-17
 */
@TableName("op_log")
public class OpLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 机构id
     */
    @TableField("org_id")
    private Long orgId;
    /**
     * 校区id
     */
    @TableField("campus_id")
    private Long campusId;
    /**
     * 员工id
     */
    @TableField("staff_id")
    private Long staffId;
    /**
     * 接口
     */
    @TableField("action_name")
    private String actionName;
    /**
     * 模块
     */
    private Integer module;
    /**
     * 操作，0-查询，1-添加，2-编辑，3-更新状态，4-删除
     */
    private Integer oper;
    /**
     * 操作的详细记录
     */
    private String description;
    /**
     * 请求参数
     */
    private String params;
    /**
     * 请求IP
     */
    private String ip;
    /**
     * 请求状态，1-成功，0-失败
     */
    private Integer status;
    /**
     * 请求结果
     */
    private String result;
    /**
     * 执行时间
     */
    private Integer time;
    /**
     * 删除标志，0-正常，1-删除
     */
    @TableField("del_flag")
    private Integer delFlag;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 模块对应表名称
     */
    @TableField("table_name")
    private String tableName;
    /**
     * 操作对象的id
     */
    @TableField("table_id")
    private Long tableId;
    /**
     * 备注，用来保存异常信息等
     */
    private String remarks;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getCampusId() {
        return campusId;
    }

    public void setCampusId(Long campusId) {
        this.campusId = campusId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public Integer getModule() {
        return module;
    }

    public void setModule(Integer module) {
        this.module = module;
    }

    public Integer getOper() {
        return oper;
    }

    public void setOper(Integer oper) {
        this.oper = oper;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
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

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "OpLog{" +
        ", id=" + id +
        ", orgId=" + orgId +
        ", campusId=" + campusId +
        ", staffId=" + staffId +
        ", actionName=" + actionName +
        ", module=" + module +
        ", oper=" + oper +
        ", description=" + description +
        ", params=" + params +
        ", ip=" + ip +
        ", status=" + status +
        ", result=" + result +
        ", time=" + time +
        ", delFlag=" + delFlag +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", tableName=" + tableName +
        ", tableId=" + tableId +
        ", remarks=" + remarks +
        "}";
    }
}
