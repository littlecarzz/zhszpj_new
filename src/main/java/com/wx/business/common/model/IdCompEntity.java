package com.wx.business.common.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 描述：
 *
 * @author littlecar
 * @date 2019/11/28 10:31
 */
@MappedSuperclass
public abstract class IdCompEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    private String lrr;
    private String lrsj;
    private String xgr;
    private String xgsj;
}
