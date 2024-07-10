package com.jxh.lease.web.app.mapper;

import com.jxh.lease.model.entity.ViewAppointment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxh.lease.web.app.vo.appointment.AppointmentItemVo;

import java.util.List;

public interface ViewAppointmentMapper extends BaseMapper<ViewAppointment> {

    List<AppointmentItemVo> listItemByUserId(Long userId);
}
