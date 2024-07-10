package com.jxh.lease.web.app.service.impl;

import com.jxh.lease.model.entity.ViewAppointment;
import com.jxh.lease.web.app.mapper.ViewAppointmentMapper;
import com.jxh.lease.web.app.service.ApartmentInfoService;
import com.jxh.lease.web.app.service.ViewAppointmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.web.app.vo.apartment.ApartmentItemVo;
import com.jxh.lease.web.app.vo.appointment.AppointmentDetailVo;
import com.jxh.lease.web.app.vo.appointment.AppointmentItemVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewAppointmentServiceImpl extends ServiceImpl<ViewAppointmentMapper, ViewAppointment>
        implements ViewAppointmentService {

    private final ViewAppointmentMapper viewAppointmentMapper;
    private final ApartmentInfoService apartmentInfoService;

    public ViewAppointmentServiceImpl(
            ViewAppointmentMapper viewAppointmentMapper,
            ApartmentInfoService apartmentInfoService
    ) {
        this.viewAppointmentMapper = viewAppointmentMapper;
        this.apartmentInfoService = apartmentInfoService;
    }

    @Override
    public List<AppointmentItemVo> listItemByUserId(Long userId) {
        return viewAppointmentMapper.listItemByUserId(userId);
    }

    @Override
    public AppointmentDetailVo getDetailById(Long id) {

        ViewAppointment viewAppointment = viewAppointmentMapper.selectById(id);
        AppointmentDetailVo agreementDetailVo = AppointmentDetailVo.builder()
                .apartmentItemVo(apartmentInfoService.selectApartmentItemVoById(viewAppointment.getApartmentId()))
                .build();
        BeanUtils.copyProperties(viewAppointment, agreementDetailVo);

        return agreementDetailVo;
    }
}
