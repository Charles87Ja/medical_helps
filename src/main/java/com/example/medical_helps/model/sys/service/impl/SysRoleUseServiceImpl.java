package com.example.medical_helps.model.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.medical_helps.model.sys.dao.SysRoleUseDao;
import com.example.medical_helps.model.sys.entity.SysRoleUse;
import com.example.medical_helps.model.sys.service.SysRoleUseService;
import org.springframework.stereotype.Service;

/**
 * 用户角色表(SysRoleUse)表服务实现类
 *
 * @author makejava
 * @since 2021-01-21 18:21:37
 */
@Service("sysRoleUseService")
public class SysRoleUseServiceImpl extends ServiceImpl<SysRoleUseDao, SysRoleUse> implements SysRoleUseService {

}