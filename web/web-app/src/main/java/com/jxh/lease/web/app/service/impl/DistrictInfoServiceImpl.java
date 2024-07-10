package com.jxh.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.model.entity.DistrictInfo;
import com.jxh.lease.web.app.service.DistrictInfoService;
import com.jxh.lease.web.app.mapper.DistrictInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class DistrictInfoServiceImpl extends ServiceImpl<DistrictInfoMapper, DistrictInfo>
    implements DistrictInfoService {

}
