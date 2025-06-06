package com.example.medical_helps.model.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.medical_helps.model.sys.entity.SysToken;
import com.example.medical_helps.model.sys.service.SysTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * token表(SysToken)表控制层
 *
 * @author makejava
 * @since 2020-12-29 09:49:44
 */
@RestController
@RequestMapping("sysToken")
public class SysTokenController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private SysTokenService sysTokenService;

    /**
     * 分页查询所有数据
     *
     * @param page     分页对象
     * @param sysToken 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<SysToken> page, SysToken sysToken) {
        return success(this.sysTokenService.page(page, new QueryWrapper<>(sysToken)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.sysTokenService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysToken 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody SysToken sysToken) {
        return success(this.sysTokenService.save(sysToken));
    }

    /**
     * 修改数据
     *
     * @param sysToken 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody SysToken sysToken) {
        return success(this.sysTokenService.updateById(sysToken));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.sysTokenService.removeByIds(idList));
    }
}