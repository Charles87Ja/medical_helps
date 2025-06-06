package com.example.medical_helps.model.sys.service;


import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.medical_helps.model.sys.entity.SysMenu;

/**
 * 菜单表(SysMenu)表服务接口
 *
 * @author makejava
 * @since 2020-12-29 09:49:09
 */
public interface SysMenuService extends IService<SysMenu> {
    public R getMenuList();

}