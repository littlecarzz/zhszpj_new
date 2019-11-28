package com.wx.business.account.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.common.collect.Lists;
import com.wx.business.common.model.IdCompEntity;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 描述：
 *
 * @author littlecar
 * @date 2019/11/28 10:17
 */
@Entity
@Table(name = "tb_account_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User extends IdCompEntity {

    /**
     * 姓名
     */
    @Column(length = 20)
    private String xm;
    /**
     * 性别码
     */
    @Column(length = 2)
    private Integer xbm;
    /**
     * 出生日期
     */
    @Column(length = 40)
    private Date csrq;
    /**
     * 身份证号
     */
    @Column(length = 40)
    private String sfzh;
    /**
     * 联系电话
     */
    @Column(length = 20)
    private String lxdh;
    /**
     * 联系手机
     */
    @Column(length = 20)
    private String lxmob;
    /**
     * Email
     */
    @Column(length = 20)
    private String email;
    /**
     * 设置的问题1
     */
    @Column(length = 255)
    private String wt1;
    /**
     * 答案1
     */
    @Column(length = 255)
    private String da1;
    /**
     * 设置的问题2
     */
    @Column(length = 255)
    private String wt2;
    /**
     * 答案2
     */
    @Column(length = 255)
    private String da2;
    /**
     * 姓名字母首拼
     */
    @Column(length = 10)
    private String zmsp;

    // 多对多定义
    @ManyToMany
    // 中间表定义,表名采用默认命名规则
    @JoinTable(name = "tb_account_user_auth", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "auth_id") })
    // Fecth策略定义
    @Fetch(FetchMode.SUBSELECT)
    // 集合按id排序.
    @OrderBy("id ASC")
    // 集合中对象id的缓存.
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonBackReference
    private List<Authority> authList = Lists.newArrayList();// 有序的关联对象集合

    private List<UserAuth> userAuthList = Lists.newArrayList();// 有序的关联对象集合

    @Transient
    private String errormsg;

    @OneToMany(mappedBy="user",fetch = FetchType.LAZY)
    @OrderBy("auth asc")
    public List<UserAuth> getUserAuthList() {
        List<UserAuth> userAList = Lists.newArrayList();
        for(UserAuth entity: userAuthList){
            //去掉已禁用的角色 1 已启用  0 已禁用  2 已禁用
            if(entity.getState().equals(1)){
                userAList.add(entity);
            }
        }
        return userAList;
    }
}
