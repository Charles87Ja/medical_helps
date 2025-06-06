package com.example.medical_helps.model.sys.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.medical_helps.Handler.AuthInterceptor;
import com.example.medical_helps.Helper.DataHelper;
import com.example.medical_helps.model.sys.entity.SysRoleUse;
import com.example.medical_helps.model.sys.entity.SysUser;
import com.example.medical_helps.model.sys.entity.vo.UserVO;
import com.example.medical_helps.model.sys.service.SysRoleUseService;
import com.example.medical_helps.model.sys.service.SysTokenService;
import com.example.medical_helps.model.sys.service.SysUserService;
import com.example.medical_helps.support.AbstractController;
import com.example.medical_helps.validator.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用户表(SysUser)表控制层
 *
 * @author makejava
 * @since 2020-12-28 15:10:57
 */

@RestController
@RequestMapping("sysUser")
public class SysUserController extends AbstractController {
    /**
     * 服务对象
     */
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysTokenService sysToken;
    @Autowired
    private SysRoleUseService sysRoleUseService;

    /**
     * 用户登录
     *
     */
    @PostMapping("login")
    public R login(@RequestBody UserVO vo, HttpSession session){
        System.out.println(vo.getUsername());
        ValidatorUtils.validateEntity(vo);
        return sysUserService.login(vo,session);
    }

    @PostMapping("register")
    public R register(@RequestBody SysUser sysUser){
        SysUser sysUser1 = sysUserService.queryByUsername(sysUser.getUsername());
        if (sysUser1==null){
            sysUserService.save(sysUser);
            return R.ok("200");
        }else
        return R.ok("用户已注册");
    }

    @PostMapping("logout")
    public R logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        String username= (String) session.getAttribute("username");
        sysToken.expireToken(username,request);
        session.setAttribute("token","");
        return R.ok("退出登录成功");
    }

    @GetMapping("/getUserMenu")
    public R getUserMenu(){
        System.out.println(getUserId());
        return sysUserService.getUserMenu(getUserId());
    }
    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param sysUser 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<SysUser> page, SysUser sysUser) {
        return R.ok(this.sysUserService.page(page, new QueryWrapper<>(sysUser)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param
     * @return 单条数据
     */
    @GetMapping("getUserInfo")
    public R selectOne() {
        return R.ok(this.sysUserService.getById(getUserId()));
    }

    /**
     * 新增数据
     *
     * @param sysUser 实体对象
     * @return 新增结果
     */
    @PostMapping("add")
    public R insert(@RequestBody SysUser sysUser) {
        return R.ok(this.sysUserService.save(sysUser));
    }

    /**
     * 修改数据
     *
     * @param sysUser 实体对象
     * @return 修改结果
     */
    @PutMapping("update")
    public R update(@RequestBody SysUser sysUser) {
        return R.ok(this.sysUserService.updateById(sysUser));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return R.ok(this.sysUserService.removeByIds(idList));
    }
    /**
     * @description:       获取用户角色
     * @menu:              这里填写方法级别分类名称（比如 多级目录/menu/menu1/menu2)
     * @status:            undone
     */
    @GetMapping("getUserPowers")
    public R getRolePowers(@RequestParam("userId") String userId){
        System.out.println(userId);
        if(userId!=null){
            return R.ok(sysRoleUseService.list(new QueryWrapper<SysRoleUse>().eq("user_id",userId)));
        }
        else {
            return R.failed("appId不能为空");
        }
    }
    @PostMapping("setUserPowers")
    public R setRolePowers(){
        DataHelper params = getParams();
        String  userid = params.getString("userId");
        Integer userId=Integer.parseInt(userid);
        System.out.println(userId);
//        int userId =(int) params.get("userId");
        JSONArray powers = (JSONArray) params.get("powers");
        System.out.println(powers);;
        if(userId!=null && powers != null){
            List<SysRoleUse> userrole = sysRoleUseService.list(new QueryWrapper<SysRoleUse>().eq("user_id", userId));
            boolean result = true;
            if(userrole.size()>=0){
                Map<String,Object> map =new HashMap<>();
                map.put("user_id",userId);
                sysRoleUseService.removeByMap(map);
            }
            if(result){
               userrole = new ArrayList<>();
               SysRoleUse vo = null;
                System.out.println(powers);;
                for (Object power:powers){
                    System.out.println("+++++++++++++++++++");
                    vo = new SysRoleUse();
                    vo.setUserId(userId);
                    vo.setRoleId((int) power);
                    userrole.add(vo);
                }
                result = sysRoleUseService.saveBatch(userrole);
                if(result){
                    return R.ok("操作成功");
                }
            }

        }
        return null;
    }

}