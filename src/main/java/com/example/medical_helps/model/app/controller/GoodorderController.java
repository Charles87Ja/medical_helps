package com.example.medical_helps.model.app.controller;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.medical_helps.model.app.entity.Good;
import com.example.medical_helps.model.app.entity.Goodorder;
import com.example.medical_helps.model.app.service.GoodService;
import com.example.medical_helps.model.app.service.GoodorderService;
import com.example.medical_helps.support.AbstractController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 订单表(Goodorder)表控制层
 *
 * @author makejava
 * @since 2022-05-24 21:59:08
 */
@RestController
@RequestMapping("goodorder")
public class GoodorderController extends AbstractController {
    /**
     * 服务对象
     */
    @Resource
    private GoodorderService goodorderService;

    @Resource
    private GoodService goodService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param goodorder 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Goodorder> page, Goodorder goodorder) {
        return success(this.goodorderService.page(page, new QueryWrapper<>(goodorder)));
    }
    @GetMapping("userOrder/{id}")
    public R userOrder(@PathVariable Serializable id) {
        if(id.equals("-1")){
            return success(this.goodorderService.list(new LambdaQueryWrapper<Goodorder>().eq(Goodorder::getUserid,getUserId())));
        }
        return success(this.goodorderService.list(new LambdaQueryWrapper<Goodorder>()
                .eq(Goodorder::getUserid,getUserId())
                .eq(Goodorder::getStatus,id)));

    }

    @GetMapping("shopOrder/{id}")
    public R shopOrder(@PathVariable Serializable id) {
        if(getUserId().equals("1")){
            if(id.equals("-1")){
                return success(this.goodorderService.list());
            }
            return success(this.goodorderService.list(new LambdaQueryWrapper<Goodorder>()
                    .eq(Goodorder::getStatus,id)));
        }
        if(id.equals("-1")){
            return success(this.goodorderService.list(new LambdaQueryWrapper<Goodorder>().eq(Goodorder::getShopuserid,getUserId())));
        }
        return success(this.goodorderService.list(new LambdaQueryWrapper<Goodorder>().
                eq(Goodorder::getShopuserid,getUserId())
               .eq(Goodorder::getStatus,id))
        );
    }




    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.goodorderService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param goodorder 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Goodorder goodorder) {
        goodorder.setTime(new Date());
        goodorder.setUserid(getUserId());
        Good byId = goodService.getById(goodorder.getGoodid());
        goodorder.setShopuserid(byId.getUserid());
        return success(this.goodorderService.save(goodorder));
    }

    /**
     * 修改数据
     *
     * @param goodorder 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Goodorder goodorder) {
        if(goodorder.getStatus()==2){
            Good good = new Good();
            good.setId(goodorder.getGoodid());
            good.setStatus(0);
        }
        if(goodorder.getStatus()==3){
            Good good = new Good();
            good.setId(goodorder.getGoodid());
            good.setStatus(0);
        }
        return success(this.goodorderService.updateById(goodorder));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.goodorderService.removeByIds(idList));
    }
}

