package com.example.medical_helps.support;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.example.medical_helps.Handler.AuthInterceptor;
import com.example.medical_helps.Helper.DataHelper;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.Map;


@RestController
public class AbstractController extends ApiController {

    public static String getUserId(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        String userId = JWT.decode(token).getAudience().get(0);
        return userId;
    }

    public static DataHelper getParams(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        DataHelper params = new DataHelper();
        if("GET".equals(request.getMethod())){
            Map<String,String[]> map = request.getParameterMap();
            if(map!=null){
                String[] keys = map.keySet().toString().replace("[","")
                        .replace("]","").split(",");
                for (int i=0;i<keys.length;i++){
                    String key = keys[i].replace(" ","");
                    params.put(key,request.getParameter(key));
                }
            }
        } else if("POST".equals(request.getMethod())){
            StringBuffer jb = new StringBuffer();
            String line = null;
            try {
                BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null)
                    jb.append(line);
            } catch (Exception e) { /*report an error*/ }
            JSONObject jsonObject = JSONObject.parseObject(jb.toString());
            params.putAll(jsonObject);
        }

        if(params.get("pageSize")!=null){
            Integer pageNum = Integer.parseInt(params.getString("pageNum"));
            Integer pageSize = Integer.parseInt(params.getString("pageSize"));
            PageHelper.startPage(pageNum,pageSize);
        }
        return params;
    }

}
