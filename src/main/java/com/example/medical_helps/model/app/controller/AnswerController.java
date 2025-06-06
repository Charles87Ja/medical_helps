package com.example.medical_helps.model.app.controller;



import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.medical_helps.model.app.entity.Answer;
import com.example.medical_helps.model.app.entity.Taskinfo;
import com.example.medical_helps.model.app.service.AnswerService;
import com.example.medical_helps.model.sys.entity.SysUser;
import com.example.medical_helps.model.sys.service.SysUserService;
import com.example.medical_helps.support.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 总结表(Answer)表控制层
 *
 * @author makejava
 * @since 2022-05-19 19:07:10
 */
@RestController
@RequestMapping("answer")
public class AnswerController extends AbstractController {
    /**
     * 服务对象
     */
    @Resource
    private AnswerService answerService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param answer 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Answer> page, Answer answer) {
        return success(this.answerService.page(page, new QueryWrapper<>(answer)));
    }

    @GetMapping("userList")
    public R userList(Page<Answer> page, Answer answer) {
        answer.setUserid(getUserId());
        return success(this.answerService.page(page, new QueryWrapper<>(answer)));
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.answerService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param answer 实体对象
     * @return 新增结果
     */

    @Autowired
    SysUserService sysUserService;

    @PostMapping
        public R insert(@RequestBody Answer answer) {
        if(!StringUtils.isEmpty(answer.getId())){
             answer.setRestortime(new Date());
             answer.setStatus("1");
             return  success(this.answerService.updateById(answer));
        }
        SysUser byId = sysUserService.getById(getUserId());
        answer.setName(byId.getName());
        answer.setUserid(getUserId());
        answer.setTime(new Date());
        return success(this.answerService.save(answer));
    }

    @GetMapping("statistic")
    public R statistic() {
        List<Answer> list = answerService.list(new LambdaQueryWrapper<Answer>().eq(Answer::getUserid, getUserId()));
        List<Long> vo = new ArrayList<>();
        LambdaQueryWrapper<Taskinfo> wa = new LambdaQueryWrapper<>();
        wa.orderByDesc(Taskinfo::getTime);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            long count = list.stream().filter(item -> {
                Date parse = null;
                parse = item.getTime();
                int l = (int) ((new Date().getTime() - parse.getTime()) / (24 * 3600 * 1000));
                if (l == finalI) {
                    return true;
                }
                return false;
            }).count();
            vo.add(count);
        }
        return success(vo);
    }

    /**
     * 修改数据
     *
     * @param answer 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Answer answer) {
        return success(this.answerService.updateById(answer));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.answerService.removeByIds(idList));
    }
}

