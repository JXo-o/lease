package com.jxh.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.model.entity.RoomPaymentType;
import com.jxh.lease.web.app.service.RoomPaymentTypeService;
import com.jxh.lease.web.app.mapper.RoomPaymentTypeMapper;
import org.springframework.stereotype.Service;

@Service
public class RoomPaymentTypeServiceImpl extends ServiceImpl<RoomPaymentTypeMapper, RoomPaymentType>
    implements RoomPaymentTypeService {

}
