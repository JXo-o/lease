package com.jxh.lease.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxh.lease.model.entity.ViewAppointment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxh.lease.web.admin.vo.appointment.AppointmentQueryVo;
import com.jxh.lease.web.admin.vo.appointment.AppointmentVo;

public interface ViewAppointmentService extends IService<ViewAppointment> {

    IPage<AppointmentVo> pageAppointmentByQuery(IPage<AppointmentVo> appointmentVoPage, AppointmentQueryVo queryVo);
}
