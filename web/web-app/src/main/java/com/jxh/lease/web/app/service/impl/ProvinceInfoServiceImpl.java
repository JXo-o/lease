package com.jxh.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.model.entity.ProvinceInfo;
import com.jxh.lease.web.app.service.ProvinceInfoService;
import com.jxh.lease.web.app.mapper.ProvinceInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class ProvinceInfoServiceImpl extends ServiceImpl<ProvinceInfoMapper, ProvinceInfo>
    implements ProvinceInfoService {

}
