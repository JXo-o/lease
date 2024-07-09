package com.jxh.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.model.entity.RoomLabel;
import com.jxh.lease.web.admin.service.RoomLabelService;
import com.jxh.lease.web.admin.mapper.RoomLabelMapper;
import org.springframework.stereotype.Service;

@Service
public class RoomLabelServiceImpl extends ServiceImpl<RoomLabelMapper, RoomLabel>
    implements RoomLabelService{

}
