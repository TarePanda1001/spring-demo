package com.wyl.webdemo.entity.local;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wyl123
 * @since 2018-10-08
 */
public class Stu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String sno;
    private String sname;
    private String password;
    private String tno;
    private String tname;
    private String tgrade;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTgrade() {
        return tgrade;
    }

    public void setTgrade(String tgrade) {
        this.tgrade = tgrade;
    }

    @Override
    public String toString() {
        return "Stu{" +
        ", id=" + id +
        ", sno=" + sno +
        ", sname=" + sname +
        ", password=" + password +
        ", tno=" + tno +
        ", tname=" + tname +
        ", tgrade=" + tgrade +
        "}";
    }
}
