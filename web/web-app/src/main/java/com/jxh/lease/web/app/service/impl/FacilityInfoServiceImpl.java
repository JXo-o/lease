package com.jxh.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.model.entity.FacilityInfo;
import com.jxh.lease.web.app.service.FacilityInfoService;
import com.jxh.lease.web.app.mapper.FacilityInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class FacilityInfoServiceImpl extends ServiceImpl<FacilityInfoMapper, FacilityInfo>
    implements FacilityInfoService {

}
