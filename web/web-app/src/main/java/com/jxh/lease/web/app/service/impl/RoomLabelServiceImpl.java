package com.jxh.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.model.entity.RoomLabel;
import com.jxh.lease.web.app.service.RoomLabelService;
import com.jxh.lease.web.app.mapper.RoomLabelMapper;
import org.springframework.stereotype.Service;

@Service
public class RoomLabelServiceImpl extends ServiceImpl<RoomLabelMapper, RoomLabel>
    implements RoomLabelService {

}
