package com.example.medical_helps.Handler;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.medical_helps.model.sys.entity.SysUser;
import com.example.medical_helps.model.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private SysUserService sysUserService;
    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登录状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public String userId;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
//        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
//        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
//        httpServletResponse.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
//        httpServletResponse.setHeader("X-Powered-By","Jetty");

        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
//        //检查是否有passtoken注释，有则跳过认证
//        if (method.isAnnotationPresent(PassToken.class)) {
//            PassToken passToken = method.getAnnotation(PassToken.class);
//            if (passToken.required()) {
//                return true;
//            }
//        }
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        JSONObject res = new JSONObject();
        if (token == null) {
            PrintWriter out = httpServletResponse.getWriter();
            res.put("msg", "未登录，请重新登录");
            res.put("code", 401);
            out.append(res.toString());
            return false;
        }
        // 获取 token 中的 user id
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            PrintWriter out = httpServletResponse.getWriter();
            res.put("msg", "登录状态有误，请重新登录");
            res.put("code", 401);
            out.append(res.toString());
            return false;
        }
        SysUser user = sysUserService.getById(userId);
        if (user == null) {
            PrintWriter out = httpServletResponse.getWriter();
            res.put("msg", "登录状态有误，请重新登录");
            res.put("code", 401);
            out.append(res.toString());
            return false;
        }
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            PrintWriter out = httpServletResponse.getWriter();
            res.put("msg", "登录超时,请重新登录");
            res.put("code", 401);
            out.append(res.toString());
            return false;
        }
        this.userId=userId;
        return true;
//        //检查有没有需要用户权限的注解
//        if (method.isAnnotationPresent(UserLoginToken.class)) {
//            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
//            if (userLoginToken.required()) {
////                 执行认证
//                if (token == null) {
//                    PrintWriter out = httpServletResponse.getWriter();
//                    res.put("msg", "未登录，请重新登录");
//                    res.put("code", 401);
//                    out.append(res.toString());
//                    return false;
//                }
//                // 获取 token 中的 user id
//                String userId;
//                try {
//                    userId = JWT.decode(token).getAudience().get(0);
//                } catch (JWTDecodeException j) {
//                    PrintWriter out = httpServletResponse.getWriter();
//                    res.put("msg", "登录状态有误，请重新登录");
//                    res.put("code", 401);
//                    out.append(res.toString());
//                    return false;
//                }
//                SysUser user = sysUserService.getById(userId);
//                if (user == null) {
//                    PrintWriter out = httpServletResponse.getWriter();
//                    res.put("msg", "登录状态有误，请重新登录");
//                    res.put("code", 401);
//                    out.append(res.toString());
//                    return false;
//                }
//                // 验证 token
//                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
//                try {
//                    jwtVerifier.verify(token);
//                } catch (JWTVerificationException e) {
//                    PrintWriter out = httpServletResponse.getWriter();
//                    res.put("msg", "登录超时,请重新登录");
//                    res.put("code", 401);
//                    out.append(res.toString());
//                    return false;
//                }
//                return true;
//            }
//       }
//        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
