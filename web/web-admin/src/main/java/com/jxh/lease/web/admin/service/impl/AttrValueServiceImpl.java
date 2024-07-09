package com.jxh.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.model.entity.AttrValue;
import com.jxh.lease.web.admin.service.AttrValueService;
import com.jxh.lease.web.admin.mapper.AttrValueMapper;
import org.springframework.stereotype.Service;

@Service
public class AttrValueServiceImpl extends ServiceImpl<AttrValueMapper, AttrValue>
    implements AttrValueService{

}
