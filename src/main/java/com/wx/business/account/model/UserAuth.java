package com.wx.business.account.model;

import com.wx.business.basic.model.Jyjxx;
import com.wx.business.basic.model.School;
import com.wx.business.basic.model.Student;
import com.wx.business.common.model.IdCompEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * 描述：
 *
 * @author littlecar
 * @date 2019/11/28 10:43
 */
@Entity
@Table(name = "tb_account_user_auth")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserAuth extends IdCompEntity {

    /**
     * user表id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
    /**
     * authority表id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTH_ID")
    private Authority auth;
    /**
     * 省县码
     * 超级管理员的省县码为330000;
     * 记录的一般为添加者所在的机构sxm
     * 添加者为校级时为sxm+xxdm
     */
    private String sxm;
    /**
     * 教育局信息ID(非教育局用户默认为0)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JYJXX_ID")
    private Jyjxx jyjxx;
    /**
     * 学校信息ID(非学校用户默认为0)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "XXXX_ID")
    private School school;
    /**
     * 学生信息ID(非学生用户默认为0)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "XSXX_ID")
    private Student student;
    /**
     * 区县教育局普通用户管理学段 id
     * 若管理多个学段，则多个学段之间“,”隔开
     */
    private String glxd;
    /**
     * 是否启用 0 否  1 是  2 系统禁用
     */
    private Integer state;

    /**
     * @Title: getSxm
     * @Description: 返回UserAuth所在机构的sxm
     * @param UserAuth
     * @return String
     */
    // 非持久化属性.
    @Transient
    public String getUserAuthSxm() {
        if (school != null) {
            return school.getSxm();
        } else if (jyjxx != null) {
            return jyjxx.getSxm();
        } else {// 若user没有所属学校和教育局 则该user为省厅用户或超级管理员用户
            return Constants.STSXM;
        }
    }

    /**
     * @Title: getSxmMc
     * @Description: 返回UserAuth所在机构的sxm
     * @param UserAuth
     * @return String
     */
    // 非持久化属性.
    @Transient
    public String getUserAuthSxmmc() {
        if (school != null) {
            return school.getJyjxx().getBjszd();
        } else if (jyjxx != null) {
            return jyjxx.getBjszd();
        } else {// 若user没有所属学校和教育局 则该user为省厅用户或超级管理员用户
            return Constants.STSXMMC;
        }
    }
}
