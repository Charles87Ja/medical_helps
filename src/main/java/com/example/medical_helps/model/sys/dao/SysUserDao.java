package com.example.medical_helps.model.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.medical_helps.Helper.DataHelper;
import com.example.medical_helps.model.sys.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户表(SysUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-28 15:10:56
 */
@Component
public interface SysUserDao extends BaseMapper<SysUser> {
    List<DataHelper> getUserMenu(@Param("userId") String userId);
}