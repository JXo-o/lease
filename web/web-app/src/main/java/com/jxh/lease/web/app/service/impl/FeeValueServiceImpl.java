package com.jxh.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.model.entity.FeeValue;
import com.jxh.lease.web.app.service.FeeValueService;
import com.jxh.lease.web.app.mapper.FeeValueMapper;
import org.springframework.stereotype.Service;

@Service
public class FeeValueServiceImpl extends ServiceImpl<FeeValueMapper, FeeValue>
    implements FeeValueService{

}
