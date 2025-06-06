package com.example.medical_helps.model.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.medical_helps.model.app.dao.GoodDao;
import com.example.medical_helps.model.app.entity.Good;
import com.example.medical_helps.model.app.service.GoodService;
import org.springframework.stereotype.Service;

/**
 * 商品表(Good)表服务实现类
 *
 * @author makejava
 * @since 2022-05-24 21:57:20
 */
@Service("goodService")
public class GoodServiceImpl extends ServiceImpl<GoodDao, Good> implements GoodService {

}

