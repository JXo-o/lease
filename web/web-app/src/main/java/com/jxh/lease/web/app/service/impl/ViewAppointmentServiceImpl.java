package com.jxh.lease.web.app.service.impl;

import com.jxh.lease.common.redis.RedisConstant;
import com.jxh.lease.model.entity.ViewAppointment;
import com.jxh.lease.web.app.mapper.ViewAppointmentMapper;
import com.jxh.lease.web.app.service.ApartmentInfoService;
import com.jxh.lease.web.app.service.ViewAppointmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.web.app.vo.apartment.ApartmentItemVo;
import com.jxh.lease.web.app.vo.appointment.AppointmentDetailVo;
import com.jxh.lease.web.app.vo.appointment.AppointmentItemVo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class ViewAppointmentServiceImpl extends ServiceImpl<ViewAppointmentMapper, ViewAppointment>
        implements ViewAppointmentService {

    private final ViewAppointmentMapper viewAppointmentMapper;
    private final ApartmentInfoService apartmentInfoService;
    private final RedisTemplate<String, Object> stringObjectRedisTemplate;

    public ViewAppointmentServiceImpl(
            ViewAppointmentMapper viewAppointmentMapper,
            ApartmentInfoService apartmentInfoService,
            RedisTemplate<String, Object> stringObjectRedisTemplate
    ) {
        this.viewAppointmentMapper = viewAppointmentMapper;
        this.apartmentInfoService = apartmentInfoService;
        this.stringObjectRedisTemplate = stringObjectRedisTemplate;
    }

    @Override
    public List<AppointmentItemVo> listItemByUserId(Long userId) {
        return viewAppointmentMapper.listItemByUserId(userId);
    }

    @Override
    public AppointmentDetailVo getDetailById(Long id) {
        return Optional.ofNullable(
                (AppointmentDetailVo) stringObjectRedisTemplate.opsForValue().get(RedisConstant.APP_APPOINTMENT_PREFIX + id)
        ).orElseGet(() -> getAppointmentDetailFromDatabase(id));
    }

    public AppointmentDetailVo getAppointmentDetailFromDatabase(Long id) {
        ViewAppointment viewAppointment = viewAppointmentMapper.selectById(id);
        AppointmentDetailVo agreementDetailVo = AppointmentDetailVo.builder()
                .apartmentItemVo(apartmentInfoService.selectApartmentItemVoById(viewAppointment.getApartmentId()))
                .build();
        BeanUtils.copyProperties(viewAppointment, agreementDetailVo);
        stringObjectRedisTemplate.opsForValue().set(
                RedisConstant.APP_APPOINTMENT_PREFIX + id,
                agreementDetailVo,
                RedisConstant.DEFAULT_EXPIRE_TIME,
                TimeUnit.SECONDS
        );
        return agreementDetailVo;
    }

}
