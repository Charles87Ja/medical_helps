package com.example.medical_helps.model.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.medical_helps.Helper.DataHelper;
import com.example.medical_helps.model.sys.entity.SysMenu;

import java.util.List;

/**
 * 菜单表(SysMenu)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-29 09:49:09
 */
public interface SysMenuDao extends BaseMapper<SysMenu> {
    List<DataHelper> getMenuList();
}