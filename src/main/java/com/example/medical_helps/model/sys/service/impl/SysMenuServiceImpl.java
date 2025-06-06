package com.example.medical_helps.model.sys.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.medical_helps.Helper.DataHelper;
import com.example.medical_helps.model.sys.dao.SysMenuDao;
import com.example.medical_helps.model.sys.entity.SysMenu;
import com.example.medical_helps.model.sys.service.SysMenuService;
import com.example.medical_helps.utils.TreeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单表(SysMenu)表服务实现类
 *
 * @author makejava
 * @since 2020-12-29 09:49:10
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {

    @Resource
    SysMenuDao sysMenuDao;

    @Override
    public R getMenuList() {
        List<DataHelper> menuList = sysMenuDao.getMenuList();
        return R.ok(TreeUtils.listToTree(menuList));
    }
}