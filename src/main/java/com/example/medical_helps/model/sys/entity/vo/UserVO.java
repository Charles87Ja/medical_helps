package com.example.medical_helps.model.sys.entity.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户表(SysUser)表实体类
 *
 * @author makejava
 * @since 2020-12-28 15:10:55
 */
@SuppressWarnings("serial")
public class UserVO extends Model<UserVO> {

    private Long id;
    //用户名
    private String username;
    //密码
    private String password;
    //真实姓名
    private String name;
    //新增时间
    private String addtime;
    //审核状态 0 未通过 1通过
    private Integer isApply;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public Integer getIsApply() {
        return isApply;
    }

    public void setIsApply(Integer isApply) {
        this.isApply = isApply;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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