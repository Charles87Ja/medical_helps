package com.example.medical_helps.model.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.medical_helps.model.sys.entity.SysMenu;
import com.example.medical_helps.model.sys.service.SysMenuService;
import com.example.medical_helps.support.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

/**
 * 菜单表(SysMenu)表控制层
 *
 * @author makejava
 * @since 2020-12-29 09:49:10
 */
@RestController
@RequestMapping("sysMenu")
public class SysMenuController extends AbstractController {
    /**
     * 服务对象
     */
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param sysMenu 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<SysMenu> page, SysMenu sysMenu) {
        return R.ok(this.sysMenuService.page(page, new QueryWrapper<>(sysMenu)));
    }
    /**
     * @description:       查询菜单列表树擦查询
     * @menu:              这里填写方法级别分类名称（比如 多级目录/menu/menu1/menu2)
     * @version:           1.0
     */
    @GetMapping("tree")
    public R getMenuList() {
        return R.ok(sysMenuService.getMenuList());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return R.ok(this.sysMenuService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysMenu 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody SysMenu sysMenu) {
        return R.ok(this.sysMenuService.save(sysMenu));
    }

    /**
     * 修改数据
     *
     * @param sysMenu 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody SysMenu sysMenu) {
        return R.ok(this.sysMenuService.updateById(sysMenu));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return R.ok(this.sysMenuService.removeByIds(idList));
    }
}