package com.powernode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.powernode.domain.LoginSysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface LoginSysUserMapper extends BaseMapper<LoginSysUser> {
    /**
     * 根据用户标识查询用户的权限集合
     * @param userId
     * @return
     */
    Set<String> selectpermsByUserId(Long userId);
}