package com.example.medical_helps.model.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.medical_helps.model.sys.entity.SysToken;
import com.example.medical_helps.model.sys.entity.SysUser;

import javax.servlet.http.HttpServletRequest;

/**
 * token表(SysToken)表服务接口
 *
 * @author makejava
 * @since 2020-12-28 16:04:11
 */
public interface SysTokenService extends IService<SysToken> {
    SysToken queryByToken(String token);
    String createToken(SysUser user);
    void expireToken(String userId, HttpServletRequest request);
}