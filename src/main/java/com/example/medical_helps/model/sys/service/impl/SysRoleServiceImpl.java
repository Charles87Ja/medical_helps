package com.example.medical_helps.model.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.medical_helps.model.sys.dao.SysRoleDao;
import com.example.medical_helps.model.sys.entity.SysRole;
import com.example.medical_helps.model.sys.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色表(SysRole)表服务实现类
 *
 * @author makejava
 * @since 2020-12-29 09:49:16
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

}