package com.jxh.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.model.entity.ApartmentFeeValue;
import com.jxh.lease.web.admin.service.ApartmentFeeValueService;
import com.jxh.lease.web.admin.mapper.ApartmentFeeValueMapper;
import org.springframework.stereotype.Service;

@Service
public class ApartmentFeeValueServiceImpl extends ServiceImpl<ApartmentFeeValueMapper, ApartmentFeeValue>
    implements ApartmentFeeValueService{

}
