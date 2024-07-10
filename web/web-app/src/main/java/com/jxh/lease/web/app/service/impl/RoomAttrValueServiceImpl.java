package com.jxh.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.model.entity.RoomAttrValue;
import com.jxh.lease.web.app.service.RoomAttrValueService;
import com.jxh.lease.web.app.mapper.RoomAttrValueMapper;
import org.springframework.stereotype.Service;

@Service
public class RoomAttrValueServiceImpl extends ServiceImpl<RoomAttrValueMapper, RoomAttrValue>
    implements RoomAttrValueService {

}
