package com.wyl.webdemo.entity.hermes;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 课程学生关联表
 * </p>
 *
 * @author wyl123
 * @since 2018-11-02
 */
@TableName("org_student_course")
public class OrgStudentCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 机构ID
     */
    @TableField("org_id")
    private Long orgId;
    /**
     * 冗余真正的课程Id,下面的cours_id实际上为班级ID
     */
    @TableField("real_course_id")
    private Long realCourseId;
    /**
     * 课程ID
     */
    @TableField("course_id")
    private Long courseId;
    /**
     * 学生ID
     */
    @TableField("student_id")
    private Long studentId;
    /**
     * 学生名称
     */
    @TableField("student_name")
    private String studentName;
    /**
     * 学生手机号
     */
    @TableField("student_mobile")
    private String studentMobile;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 删除状态 0:正常, 1:删除
     */
    @TableField("del_status")
    private Integer delStatus;
    /**
     * 0：默认，1：已经退班，2已经转班
     */
    private Integer status;
    /**
     * 交易状态,1:已支付, 0:未支付
     */
    @TableField("trade_complete")
    private Integer tradeComplete;
    /**
     * 合同课次,1v1是填写的,班课默认0
     */
    @TableField("lesson_count")
    private Integer lessonCount;
    /**
     * 按次，2按小时,3按半小时,历史未设置数据-1
     */
    @TableField("charge_unit")
    private Integer chargeUnit;
    /**
     * 报名单号
     */
    @TableField("signup_purchase_id")
    private Long signupPurchaseId;


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

    public Long getRealCourseId() {
        return realCourseId;
    }

    public void setRealCourseId(Long realCourseId) {
        this.realCourseId = realCourseId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentMobile() {
        return studentMobile;
    }

    public void setStudentMobile(String studentMobile) {
        this.studentMobile = studentMobile;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTradeComplete() {
        return tradeComplete;
    }

    public void setTradeComplete(Integer tradeComplete) {
        this.tradeComplete = tradeComplete;
    }

    public Integer getLessonCount() {
        return lessonCount;
    }

    public void setLessonCount(Integer lessonCount) {
        this.lessonCount = lessonCount;
    }

    public Integer getChargeUnit() {
        return chargeUnit;
    }

    public void setChargeUnit(Integer chargeUnit) {
        this.chargeUnit = chargeUnit;
    }

    public Long getSignupPurchaseId() {
        return signupPurchaseId;
    }

    public void setSignupPurchaseId(Long signupPurchaseId) {
        this.signupPurchaseId = signupPurchaseId;
    }

    @Override
    public String toString() {
        return "OrgStudentCourse{" +
        ", id=" + id +
        ", orgId=" + orgId +
        ", realCourseId=" + realCourseId +
        ", courseId=" + courseId +
        ", studentId=" + studentId +
        ", studentName=" + studentName +
        ", studentMobile=" + studentMobile +
        ", createTime=" + createTime +
        ", delStatus=" + delStatus +
        ", status=" + status +
        ", tradeComplete=" + tradeComplete +
        ", lessonCount=" + lessonCount +
        ", chargeUnit=" + chargeUnit +
        ", signupPurchaseId=" + signupPurchaseId +
        "}";
    }
}
