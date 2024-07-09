package com.jxh.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxh.lease.model.entity.SystemUser;
import com.jxh.lease.web.admin.mapper.SystemPostMapper;
import com.jxh.lease.web.admin.mapper.SystemUserMapper;
import com.jxh.lease.web.admin.service.SystemUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.jxh.lease.web.admin.vo.system.user.SystemUserQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser>
        implements SystemUserService {

    private final SystemUserMapper systemUserMapper;
    private final SystemPostMapper systemPostMapper;

    public SystemUserServiceImpl(SystemUserMapper systemUserMapper, SystemPostMapper systemPostMapper) {
        this.systemUserMapper = systemUserMapper;
        this.systemPostMapper = systemPostMapper;
    }

    @Override
    public IPage<SystemUserItemVo> pageSystemUserItemByQuery(IPage<SystemUserItemVo> page, SystemUserQueryVo queryVo) {
        return systemUserMapper.pageSystemUserItemByQuery(page, queryVo);
    }

    @Override
    public SystemUserItemVo getSystemUserById(Long id) {

        SystemUserItemVo systemUserItemVo = new SystemUserItemVo();
        BeanUtils.copyProperties(systemUserMapper.selectById(id), systemUserItemVo);
        systemUserItemVo.setPostName(systemPostMapper.selectById(systemUserItemVo.getPostId()).getName());

        return systemUserItemVo;
    }
}
