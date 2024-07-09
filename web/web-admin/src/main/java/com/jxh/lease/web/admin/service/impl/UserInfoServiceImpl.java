package com.jxh.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.model.entity.UserInfo;
import com.jxh.lease.web.admin.service.UserInfoService;
import com.jxh.lease.web.admin.mapper.UserInfoMapper;
import com.jxh.lease.web.admin.vo.user.UserInfoQueryVo;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

    private final UserInfoMapper userInfoMapper;

    public UserInfoServiceImpl(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public IPage<UserInfo> pageUserInfoByQuery(IPage<UserInfo> page, UserInfoQueryVo queryVo) {
        return userInfoMapper.pageUserInfoByQuery(page, queryVo);
    }
}
