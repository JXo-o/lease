package com.jxh.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.model.entity.GraphInfo;
import com.jxh.lease.web.app.service.GraphInfoService;
import com.jxh.lease.web.app.mapper.GraphInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class GraphInfoServiceImpl extends ServiceImpl<GraphInfoMapper, GraphInfo>
    implements GraphInfoService {

}
