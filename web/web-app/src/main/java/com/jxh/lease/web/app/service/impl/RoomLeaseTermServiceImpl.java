package com.jxh.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.model.entity.RoomLeaseTerm;
import com.jxh.lease.web.app.service.RoomLeaseTermService;
import com.jxh.lease.web.app.mapper.RoomLeaseTermMapper;
import org.springframework.stereotype.Service;

@Service
public class RoomLeaseTermServiceImpl extends ServiceImpl<RoomLeaseTermMapper, RoomLeaseTerm>
    implements RoomLeaseTermService {

}
