package com.example.medical_helps.model.sys.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * 用户角色表(SysRoleUse)表实体类
 *
 * @author makejava
 * @since 2021-01-21 18:21:35
 */
@SuppressWarnings("serial")
public class SysRoleUse extends Model<SysRoleUse> {

    private Integer id;
    //用户ID
    private Integer userId;
    //角色ID
    private Integer roleId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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