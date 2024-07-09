package com.jxh.lease.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxh.lease.model.entity.SystemUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxh.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.jxh.lease.web.admin.vo.system.user.SystemUserQueryVo;

public interface SystemUserService extends IService<SystemUser> {

    IPage<SystemUserItemVo> pageSystemUserItemByQuery(IPage<SystemUserItemVo> page, SystemUserQueryVo queryVo);

    SystemUserItemVo getSystemUserById(Long id);
}
