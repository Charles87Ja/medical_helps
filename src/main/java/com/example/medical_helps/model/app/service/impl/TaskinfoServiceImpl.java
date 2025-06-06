package com.example.medical_helps.model.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.medical_helps.model.app.dao.TaskinfoDao;
import com.example.medical_helps.model.app.entity.Taskinfo;
import com.example.medical_helps.model.app.service.AnswerService;
import com.example.medical_helps.model.app.service.TaskService;
import org.springframework.stereotype.Service;

/**
 * 任务表(Taskinfo)表服务实现类
 *
 * @author makejava
 * @since 2022-05-19 19:07:37
 */
@Service("taskinfoService")
public class TaskinfoServiceImpl extends ServiceImpl<TaskinfoDao, Taskinfo> implements TaskService {

}

