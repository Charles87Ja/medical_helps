package com.example.medical_helps.model.sys.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.medical_helps.model.sys.entity.SysUser;
import com.example.medical_helps.model.sys.entity.vo.UserVO;

import javax.servlet.http.HttpSession;

/**
 * 用户表(SysUser)表服务接口
 *
 * @author makejava
 * @since 2020-12-28 15:10:56
 */
public interface SysUserService extends IService<SysUser> {
    R login(UserVO vo, HttpSession session);

    SysUser queryByUsername(String mobile);

    R getUserMenu(String userId);

}