package com.jxh.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxh.lease.model.entity.SystemUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxh.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.jxh.lease.web.admin.vo.system.user.SystemUserQueryVo;

public interface SystemUserMapper extends BaseMapper<SystemUser> {

    IPage<SystemUserItemVo> pageSystemUserItemByQuery(IPage<SystemUserItemVo> page, SystemUserQueryVo queryVo);

    SystemUser selectOneByUsername(String username);
}