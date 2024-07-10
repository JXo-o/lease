package com.jxh.lease.web.app.service;

import com.jxh.lease.model.entity.ViewAppointment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxh.lease.web.app.vo.appointment.AppointmentDetailVo;
import com.jxh.lease.web.app.vo.appointment.AppointmentItemVo;

import java.util.List;

public interface ViewAppointmentService extends IService<ViewAppointment> {
    List<AppointmentItemVo> listItemByUserId(Long userId);

    AppointmentDetailVo getDetailById(Long id);
}
