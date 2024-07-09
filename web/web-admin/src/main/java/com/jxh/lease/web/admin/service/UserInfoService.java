package com.jxh.lease.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxh.lease.model.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxh.lease.web.admin.vo.user.UserInfoQueryVo;

public interface UserInfoService extends IService<UserInfo> {

    IPage<UserInfo> pageUserInfoByQuery(IPage<UserInfo> page, UserInfoQueryVo queryVo);
}
