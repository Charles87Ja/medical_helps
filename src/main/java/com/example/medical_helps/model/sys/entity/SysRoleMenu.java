package com.example.medical_helps.model.sys.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * 菜单角色表(SysRoleMenu)表实体类
 *
 * @author makejava
 * @since 2021-01-21 13:57:20
 */
@SuppressWarnings("serial")
public class SysRoleMenu extends Model<SysRoleMenu> {

    private Integer id;
    //角色ID
    private Integer roleId;
    //菜单id
    private Integer menuId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
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