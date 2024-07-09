package com.jxh.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.model.entity.GraphInfo;
import com.jxh.lease.web.admin.service.GraphInfoService;
import com.jxh.lease.web.admin.mapper.GraphInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class GraphInfoServiceImpl extends ServiceImpl<GraphInfoMapper, GraphInfo>
    implements GraphInfoService{

}
