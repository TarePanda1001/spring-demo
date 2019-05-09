package com.wyl.webdemo.entity.local;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.wyl.webdemo.annotation.FieldProp;
import com.wyl.webdemo.common.enums.DataStatus;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wyl123
 * @since 2018-10-08
 */
@TableName("student_info")
public class StudentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @FieldProp(name = "name", label = "姓名")
    private String name;
    @FieldProp(name = "age", label = "年龄")
    private Integer age;
    @FieldProp(name = "number", label = "编号")
    private Integer number;
    @FieldProp(name = "passWord", label = "密码", needDetail = false)
    @TableField("pass_word")
    private String passWord;
    @FieldProp(name = "isDel", label = "状态", needChange = true, targetEnum = DataStatus.class)
    @TableField("is_del")
    private Integer isDel;

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

}
