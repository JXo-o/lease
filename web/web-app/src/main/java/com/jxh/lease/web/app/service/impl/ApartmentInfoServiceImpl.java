package com.jxh.lease.web.app.service.impl;

import com.jxh.lease.model.entity.ApartmentInfo;
import com.jxh.lease.web.app.mapper.ApartmentInfoMapper;
import com.jxh.lease.web.app.service.ApartmentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ApartmentInfoServiceImpl extends ServiceImpl<ApartmentInfoMapper, ApartmentInfo>
        implements ApartmentInfoService {
}
