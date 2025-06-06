package com.example.medical_helps.model.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.medical_helps.model.app.dao.AnswerDao;
import com.example.medical_helps.model.app.entity.Answer;
import com.example.medical_helps.model.app.service.AnswerService;
import org.springframework.stereotype.Service;

/**
 * 总结表(Answer)表服务实现类
 *
 * @author makejava
 * @since 2022-05-19 19:07:14
 */
@Service("answerService")
public class AnswerServiceImpl extends ServiceImpl<AnswerDao, Answer> implements AnswerService {

}

