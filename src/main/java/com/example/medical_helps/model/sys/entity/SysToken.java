package com.example.medical_helps.model.sys.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * token表(SysToken)表实体类
 *
 * @author makejava
 * @since 2020-12-28 16:04:10
 */
@SuppressWarnings("serial")
public class SysToken extends Model<SysToken> {

    private String id;
    //用户id
    private String userid;
    //密码
    private String token;
    //新增时间
    private Date addtime;
    //过期时间
    private Date expiratedtime;


    public String getId() {
        return id;
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getExpiratedtime() {
        return expiratedtime;
    }

    public void setExpiratedtime(Date expiratedtime) {
        this.expiratedtime = expiratedtime;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}