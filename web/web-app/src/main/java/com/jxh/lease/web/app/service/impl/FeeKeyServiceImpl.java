package com.jxh.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.model.entity.FeeKey;
import com.jxh.lease.web.app.service.FeeKeyService;
import com.jxh.lease.web.app.mapper.FeeKeyMapper;
import org.springframework.stereotype.Service;

@Service
public class FeeKeyServiceImpl extends ServiceImpl<FeeKeyMapper, FeeKey>
    implements FeeKeyService{

}
