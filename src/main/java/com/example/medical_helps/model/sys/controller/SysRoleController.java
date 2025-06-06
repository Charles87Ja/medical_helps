package com.example.medical_helps.model.sys.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.medical_helps.Helper.DataHelper;
import com.example.medical_helps.model.sys.entity.SysRole;
import com.example.medical_helps.model.sys.entity.SysRoleMenu;
import com.example.medical_helps.model.sys.service.SysRoleMenuService;
import com.example.medical_helps.model.sys.service.SysRoleService;
import com.example.medical_helps.support.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色表(SysRole)表控制层
 *
 * @author makejava
 * @since 2020-12-29 09:49:16
 */
@RestController
@RequestMapping("sysRole")
public class SysRoleController extends AbstractController {
    /**
     * 服务对象
     */
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param sysRole 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<SysRole> page, SysRole sysRole) {
        return R.ok(this.sysRoleService.page(page, new QueryWrapper<>(sysRole)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return R.ok(this.sysRoleService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysRole 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody SysRole sysRole) {
        return R.ok(this.sysRoleService.save(sysRole));
    }

    /**
     * 修改数据
     *
     * @param sysRole 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody SysRole sysRole) {
        return R.ok(this.sysRoleService.updateById(sysRole));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return R.ok(this.sysRoleService.removeByIds(idList));
    }

    /**
     * @description:       获取角色绑定的菜单
     * @menu:              这里填写方法级别分类名称（比如 多级目录/menu/menu1/menu2)
     * @status:            undone
     */
    @GetMapping("getRolePowers")
    public R getRolePowers(@RequestParam("roleId") String roleId){
        System.out.println(roleId);
        if(roleId!=null){
            return R.ok(sysRoleMenuService.list(new QueryWrapper<SysRoleMenu>().eq("role_id",roleId)));
        }
        else {
            return R.failed("appId不能为空");
        }
    }

    @PostMapping("setRolePowers")
    public R setRolePowers(){
        DataHelper params = getParams();
//        String roleId = params.getString("roleId");
        int roleId =(int) params.get("roleId");
        JSONArray powers = (JSONArray) params.get("powers");
        System.out.println(powers);
        if(roleId!=0 && powers!=null){
            //去把角色所有的菜单全部删除
            List<SysRoleMenu> list_role = sysRoleMenuService.list(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId));
            boolean result = true;
            if(list_role.size()>=0){
                Map<String, Object> deleteMap = new HashMap<>();
                deleteMap.put("role_id",roleId);
               sysRoleMenuService.removeByMap(deleteMap);
            }
            if(result){
                list_role = new ArrayList<>();
                SysRoleMenu vo = null;
                for (Object power:powers){
                    vo = new SysRoleMenu();
                    vo.setRoleId((int) roleId);
                    vo.setMenuId((int) power);
                    list_role.add(vo);
                }
                result = sysRoleMenuService.saveBatch(list_role);
                if(result){
                    return R.ok("操作成功");
                }
            }
        }
        return null;
    }
}