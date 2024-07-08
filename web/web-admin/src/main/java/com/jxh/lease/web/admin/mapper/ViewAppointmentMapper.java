package com.jxh.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxh.lease.model.entity.ViewAppointment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxh.lease.web.admin.vo.appointment.AppointmentQueryVo;
import com.jxh.lease.web.admin.vo.appointment.AppointmentVo;

public interface ViewAppointmentMapper extends BaseMapper<ViewAppointment> {

    IPage<AppointmentVo> pageAppointmentByQuery(IPage<AppointmentVo> appointmentVoPage, AppointmentQueryVo queryVo);
}