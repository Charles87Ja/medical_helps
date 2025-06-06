package com.example.medical_helps.model.app.controller;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.medical_helps.model.app.entity.Answer;
import com.example.medical_helps.model.app.entity.Taskinfo;
import com.example.medical_helps.model.app.service.AnswerService;
import com.example.medical_helps.model.app.service.TaskService;
import com.example.medical_helps.model.sys.entity.SysUser;
import com.example.medical_helps.model.sys.service.SysUserService;
import com.example.medical_helps.support.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 任务表(Taskinfo)表控制层
 *
 * @author makejava
 * @since 2022-05-19 19:07:37
 */
@RestController
@RequestMapping("taskinfo")
public class TaskinfoController extends AbstractController {
    /**
     * 服务对象
     */
    @Resource
    private TaskService taskinfoService;

    @Resource
    private AnswerService answerService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param taskinfo 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Taskinfo> page, Taskinfo taskinfo) {
        return success(this.taskinfoService.page(page, new QueryWrapper<>(taskinfo)));
    }



    @GetMapping("userList")
    public R userList(Page<Taskinfo> page, Taskinfo taskinfo) {
        List<Answer> list = answerService.list(new LambdaQueryWrapper<Answer>().eq(Answer::getUserid, getUserId()));
        List<String> collect = list.stream().map(item -> {
            return item.getTaskid();
        }).collect(Collectors.toList());
        return success(taskinfoService.list(new LambdaQueryWrapper<Taskinfo>().in(Taskinfo::getId,collect)));
    }

    @GetMapping("teachUserList")
    public R teachUserList(Taskinfo taskinfo) {
        if(getUserId().equals("1")){
            return success(taskinfoService.list());
        }
        taskinfo.setUserid(getUserId());
        return success(taskinfoService.list(new QueryWrapper<>(taskinfo)));
    }


    @GetMapping("statistic")
    public R statistic() {
        List<Taskinfo> list = taskinfoService.list();
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
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.taskinfoService.getById(id));
    }


    @Autowired
    SysUserService sysUserService;
    /**
     * 新增数据
     *
     * @param taskinfo 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Taskinfo taskinfo) {
        SysUser byId = sysUserService.getById(getUserId());
        taskinfo.setUserid(getUserId());
        taskinfo.setTime(new Date());
        taskinfo.setName(byId.getName());
        return success(this.taskinfoService.save(taskinfo));
    }

    /**
     * 修改数据
     *
     * @param taskinfo 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Taskinfo taskinfo) {
        return success(this.taskinfoService.updateById(taskinfo));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.taskinfoService.removeByIds(idList));
    }

    @PostMapping("delete/{id}")
    public R remove(@PathVariable Serializable id) {
        answerService.remove(new LambdaQueryWrapper<Answer>().eq(Answer::getTaskid, id));
        return success(this.taskinfoService.removeById(id));
    }
}

