package com.example.medical_helps.model.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.medical_helps.Helper.DataHelper;
import com.example.medical_helps.model.sys.dao.SysUserDao;
import com.example.medical_helps.model.sys.entity.SysToken;
import com.example.medical_helps.model.sys.entity.SysUser;
import com.example.medical_helps.model.sys.entity.vo.UserVO;
import com.example.medical_helps.model.sys.service.SysTokenService;
import com.example.medical_helps.model.sys.service.SysUserService;
import com.example.medical_helps.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户表(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2020-12-28 15:10:56
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Autowired
    SysTokenService tokenService;

    @Resource
    SysUserDao sysUserDao;

    @Override
    public R login(UserVO vo, HttpSession session) {
        Map<String, Object> map = new HashMap<>(2);
        SysUser sysUser = queryByUsername(vo.getUsername());
        //Assert.isNull(sysUser,"用户账号不存在");
        if(sysUser==null){
            map.put("status", 101);
            map.put("code", "账号不存在");
            return R.ok(map);
        }
        if (!sysUser.getPassword().equals(vo.getPassword())){
            map.put("status", 102);
            map.put("code", "密码错误");
            return R.ok(map);
        }
        //获取登录token
        String token = tokenService.createToken(sysUser);
        map.put("status", 200);
        map.put("token", token);
        map.put("userId", sysUser.getName());
        return R.ok(map);
    }

    @Override
    public SysUser queryByUsername(String mobile) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",mobile);
        SysUser one = this.getOne(wrapper);
        return one;
    }

    @Override
    public R getUserMenu(String userId) {
        List<DataHelper> list = sysUserDao.getUserMenu(userId);
        return R.ok(TreeUtils.listToTree(list));
    }
}