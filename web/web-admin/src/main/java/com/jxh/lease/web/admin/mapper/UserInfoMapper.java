package com.jxh.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxh.lease.model.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxh.lease.web.admin.vo.user.UserInfoQueryVo;

public interface UserInfoMapper extends BaseMapper<UserInfo> {

    IPage<UserInfo> pageUserInfoByQuery(IPage<UserInfo> page, UserInfoQueryVo queryVo);
}