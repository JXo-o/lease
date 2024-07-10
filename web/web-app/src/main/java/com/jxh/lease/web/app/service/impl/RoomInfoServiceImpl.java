package com.jxh.lease.web.app.service.impl;

import com.jxh.lease.model.entity.RoomInfo;
import com.jxh.lease.web.app.mapper.RoomInfoMapper;
import com.jxh.lease.web.app.service.RoomInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo>
        implements RoomInfoService {

}
