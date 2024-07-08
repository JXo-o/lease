package com.jxh.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxh.lease.model.entity.ViewAppointment;
import com.jxh.lease.web.admin.mapper.ViewAppointmentMapper;
import com.jxh.lease.web.admin.service.ViewAppointmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.web.admin.vo.appointment.AppointmentQueryVo;
import com.jxh.lease.web.admin.vo.appointment.AppointmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewAppointmentServiceImpl extends ServiceImpl<ViewAppointmentMapper, ViewAppointment>
        implements ViewAppointmentService {

    private final ViewAppointmentMapper viewAppointmentMapper;

    public ViewAppointmentServiceImpl(ViewAppointmentMapper viewAppointmentMapper) {
        this.viewAppointmentMapper = viewAppointmentMapper;
    }

    @Override
    public IPage<AppointmentVo> pageAppointmentByQuery(IPage<AppointmentVo> appointmentVoPage, AppointmentQueryVo queryVo) {
        return viewAppointmentMapper.pageAppointmentByQuery(appointmentVoPage, queryVo);
    }
}




