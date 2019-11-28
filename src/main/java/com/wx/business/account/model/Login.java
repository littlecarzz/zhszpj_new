package com.wx.business.account.model;

import com.wx.business.common.model.IdCompEntity;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 描述：
 *
 * @author littlecar
 * @date 2019/11/28 10:12
 */
@Data
@Entity
@Table(name="tb_account_login")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Login extends IdCompEntity {

    /**
     * user表id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    /**
     * 登录名
     */
    @Column(length = 100,nullable = false)
    private String loginName;
    /**
     * 姓名
     */
    @Column(length = 20,nullable = false)
    private String username;
    /**
     * 密码
     */
    @Column(length = 100,nullable = false)
    private String pwd;
    /**
     * 密码状态 0 不可用  1 可用
     */
    @Column(length = 2)
    private Integer mmzt;
    /**
     * 密码修改时间
     */
    @Column(length = 100)
    private Timestamp mmsj;
    /**
     * 是否强密码 0 否 1 是
     */
    @Column(length = 2)
    private Integer sfqmm;
    /**
     * 是否启用 0 否  1 是  2 系统禁用
     */
    @Column(length = 2)
    private Integer state;
    /**
     * 用户来源
     */
    @Column(length = 2)
    private Integer userfor;
    /**
     * 用户来源ID
     */
    @Column(length = 100)
    private String  keyId;

}
