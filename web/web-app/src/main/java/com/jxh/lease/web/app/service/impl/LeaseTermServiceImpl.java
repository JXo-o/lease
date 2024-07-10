package com.jxh.lease.web.app.service.impl;

import com.jxh.lease.model.entity.LeaseTerm;
import com.jxh.lease.web.app.mapper.LeaseTermMapper;
import com.jxh.lease.web.app.service.LeaseTermService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaseTermServiceImpl extends ServiceImpl<LeaseTermMapper, LeaseTerm>
        implements LeaseTermService {

    private final LeaseTermMapper leaseTermMapper;

    public LeaseTermServiceImpl(LeaseTermMapper leaseTermMapper) {
        this.leaseTermMapper = leaseTermMapper;
    }

    @Override
    public List<LeaseTerm> listByRoomId(Long id) {
        return leaseTermMapper.selectListByRoomId(id);
    }
}
