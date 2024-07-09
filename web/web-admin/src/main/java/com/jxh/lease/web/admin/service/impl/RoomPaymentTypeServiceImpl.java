package com.jxh.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.model.entity.RoomPaymentType;
import com.jxh.lease.web.admin.service.RoomPaymentTypeService;
import com.jxh.lease.web.admin.mapper.RoomPaymentTypeMapper;
import org.springframework.stereotype.Service;

@Service
public class RoomPaymentTypeServiceImpl extends ServiceImpl<RoomPaymentTypeMapper, RoomPaymentType>
    implements RoomPaymentTypeService{

}
