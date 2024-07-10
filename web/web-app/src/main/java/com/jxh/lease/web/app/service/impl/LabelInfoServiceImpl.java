package com.jxh.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.model.entity.LabelInfo;
import com.jxh.lease.web.app.service.LabelInfoService;
import com.jxh.lease.web.app.mapper.LabelInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class LabelInfoServiceImpl extends ServiceImpl<LabelInfoMapper, LabelInfo>
    implements LabelInfoService {

}
