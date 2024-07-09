package com.jxh.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.model.entity.CityInfo;
import com.jxh.lease.web.admin.service.CityInfoService;
import com.jxh.lease.web.admin.mapper.CityInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class CityInfoServiceImpl extends ServiceImpl<CityInfoMapper, CityInfo>
    implements CityInfoService{

}
