package com.example.medical_helps.model.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.medical_helps.model.app.dao.GoodorderDao;
import com.example.medical_helps.model.app.entity.Goodorder;
import com.example.medical_helps.model.app.service.GoodorderService;
import org.springframework.stereotype.Service;

/**
 * 订单表(Goodorder)表服务实现类
 *
 * @author makejava
 * @since 2022-05-24 21:59:08
 */
@Service("goodorderService")
public class GoodorderServiceImpl extends ServiceImpl<GoodorderDao, Goodorder> implements GoodorderService {

}

