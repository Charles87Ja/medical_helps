package com.example.medical_helps.model.sys.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.medical_helps.model.sys.dao.SysTokenDao;
import com.example.medical_helps.model.sys.entity.SysToken;
import com.example.medical_helps.model.sys.entity.SysUser;
import com.example.medical_helps.model.sys.service.SysTokenService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

/**
 * token表(SysToken)表服务实现类
 *
 * @author makejava
 * @since 2020-12-28 16:04:11
 */
@Service("sysTokenService")
public class SysTokenServiceImpl extends ServiceImpl<SysTokenDao, SysToken> implements SysTokenService {
    /**
     * 12小时后过期
     */
    private final static int EXPIRE = 3600 * 12;
    @Override
    public SysToken queryByToken(String token) {
        return this.getOne(new QueryWrapper<SysToken>().eq("token", token));
    }

    @Override
    public String createToken(SysUser user) {
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);
        //生成token
        String token = generateToken(user);
        return token;
    }

    @Override
    public void expireToken(String userId, HttpServletRequest request) {
        Date now = new Date();
        UpdateWrapper<SysToken> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("userId",userId);
        SysToken tokenEntity = new SysToken();
        tokenEntity.setAddtime(now);
        tokenEntity.setExpiratedtime(now);
        this.update(tokenEntity,updateWrapper);
        HttpSession session = request.getSession();
        //这里的token是登陆时放入session的
        session.setAttribute("token",null);
    }
    private String generateToken(SysUser user){
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(user.getId()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}