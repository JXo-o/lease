package com.jxh.lease.web.app.service.impl;

import com.jxh.lease.model.entity.ViewAppointment;
import com.jxh.lease.web.app.mapper.ViewAppointmentMapper;
import com.jxh.lease.web.app.service.ViewAppointmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ViewAppointmentServiceImpl extends ServiceImpl<ViewAppointmentMapper, ViewAppointment>
        implements ViewAppointmentService {

}
