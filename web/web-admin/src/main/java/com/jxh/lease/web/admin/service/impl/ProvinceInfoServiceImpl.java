package com.jxh.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.model.entity.ProvinceInfo;
import com.jxh.lease.web.admin.service.ProvinceInfoService;
import com.jxh.lease.web.admin.mapper.ProvinceInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class ProvinceInfoServiceImpl extends ServiceImpl<ProvinceInfoMapper, ProvinceInfo>
    implements ProvinceInfoService{

}
