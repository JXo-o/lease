package com.jxh.lease.web.app.service.impl;

import com.jxh.lease.model.entity.PaymentType;
import com.jxh.lease.web.app.mapper.PaymentTypeMapper;
import com.jxh.lease.web.app.service.PaymentTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentTypeServiceImpl extends ServiceImpl<PaymentTypeMapper, PaymentType>
    implements PaymentTypeService {

    private final PaymentTypeMapper paymentTypeMapper;

    public PaymentTypeServiceImpl(PaymentTypeMapper paymentTypeMapper) {
        this.paymentTypeMapper = paymentTypeMapper;
    }

    @Override
    public List<PaymentType> listByRoomId(Long id) {
        return paymentTypeMapper.selectListByRoomId(id);
    }
}
