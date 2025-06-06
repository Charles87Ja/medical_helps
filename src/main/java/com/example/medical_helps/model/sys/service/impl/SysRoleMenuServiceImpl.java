package com.example.medical_helps.model.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.medical_helps.model.sys.dao.SysRoleMenuDao;
import com.example.medical_helps.model.sys.entity.SysRoleMenu;
import com.example.medical_helps.model.sys.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * 菜单角色表(SysRoleMenu)表服务实现类
 *
 * @author makejava
 * @since 2021-01-21 13:57:21
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenu> implements SysRoleMenuService {

}