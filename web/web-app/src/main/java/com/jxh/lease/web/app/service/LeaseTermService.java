package com.jxh.lease.web.app.service;

import com.jxh.lease.model.entity.LeaseTerm;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface LeaseTermService extends IService<LeaseTerm> {
    List<LeaseTerm> listByRoomId(Long id);
}
