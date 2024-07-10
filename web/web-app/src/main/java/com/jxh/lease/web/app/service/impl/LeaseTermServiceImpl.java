package com.jxh.lease.web.app.service.impl;

import com.jxh.lease.model.entity.LeaseTerm;
import com.jxh.lease.web.app.mapper.LeaseTermMapper;
import com.jxh.lease.web.app.service.LeaseTermService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class LeaseTermServiceImpl extends ServiceImpl<LeaseTermMapper, LeaseTerm>
        implements LeaseTermService {

}
