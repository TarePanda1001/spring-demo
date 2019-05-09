package com.wyl.webdemo.entity.local;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wyl123
 * @since 2018-11-02
 */
@TableName("operation_record")
public class OperationRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("org_id")
    private Long orgId;
    @TableField("cascade_id")
    private Long cascadeId;
    private Integer type;
    private String content;
    private String param;
    @TableField("operation_time")
    private Date operationTime;
    private Integer status;
    @TableField("cost_time")
    private Integer costTime;


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

    public Long getCascadeId() {
        return cascadeId;
    }

    public void setCascadeId(Long cascadeId) {
        this.cascadeId = cascadeId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCostTime() {
        return costTime;
    }

    public void setCostTime(Integer costTime) {
        this.costTime = costTime;
    }

    @Override
    public String toString() {
        return "OperationRecord{" +
        ", id=" + id +
        ", orgId=" + orgId +
        ", cascadeId=" + cascadeId +
        ", type=" + type +
        ", content=" + content +
        ", param=" + param +
        ", operationTime=" + operationTime +
        ", status=" + status +
        ", costTime=" + costTime +
        "}";
    }
}
