package com.jxh.lease.web.app.service.impl;

import com.jxh.lease.common.redis.RedisConstant;
import com.jxh.lease.model.entity.ApartmentInfo;
import com.jxh.lease.model.entity.FacilityInfo;
import com.jxh.lease.model.entity.LabelInfo;
import com.jxh.lease.model.enums.ItemType;
import com.jxh.lease.web.app.mapper.*;
import com.jxh.lease.web.app.service.ApartmentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.web.app.vo.apartment.ApartmentDetailVo;
import com.jxh.lease.web.app.vo.apartment.ApartmentItemVo;
import com.jxh.lease.web.app.vo.graph.GraphVo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class ApartmentInfoServiceImpl extends ServiceImpl<ApartmentInfoMapper, ApartmentInfo>
        implements ApartmentInfoService {

    private final ApartmentInfoMapper apartmentInfoMapper;
    private final LabelInfoMapper labelInfoMapper;
    private final GraphInfoMapper graphInfoMapper;
    private final RoomInfoMapper roomInfoMapper;
    private final FacilityInfoMapper facilityInfoMapper;
    private final RedisTemplate<String, Object> stringObjectRedisTemplate;

    public ApartmentInfoServiceImpl(
            ApartmentInfoMapper apartmentInfoMapper,
            LabelInfoMapper labelInfoMapper,
            GraphInfoMapper graphInfoMapper,
            RoomInfoMapper roomInfoMapper,
            FacilityInfoMapper facilityInfoMapper,
            RedisTemplate<String, Object> stringObjectRedisTemplate
    ) {
        this.apartmentInfoMapper = apartmentInfoMapper;
        this.labelInfoMapper = labelInfoMapper;
        this.graphInfoMapper = graphInfoMapper;
        this.roomInfoMapper = roomInfoMapper;
        this.facilityInfoMapper = facilityInfoMapper;
        this.stringObjectRedisTemplate = stringObjectRedisTemplate;
    }

    @Override
    public ApartmentItemVo selectApartmentItemVoById(Long id) {
        ApartmentDetailVo apartmentDetailVo = Optional.ofNullable(
                (ApartmentDetailVo) stringObjectRedisTemplate.opsForValue().get(RedisConstant.APP_APARTMENT_PREFIX + id)
        ).orElseGet(() -> getApartmentDetailFromDatabase(id));
        ApartmentItemVo apartmentItemVo = ApartmentItemVo.builder().build();
        BeanUtils.copyProperties(apartmentDetailVo, apartmentItemVo);
        return apartmentItemVo;
    }

    @Override
    public ApartmentDetailVo getApartmentDetailById(Long id) {
        return Optional.ofNullable(
                (ApartmentDetailVo) stringObjectRedisTemplate.opsForValue().get(RedisConstant.APP_APARTMENT_PREFIX + id)
        ).orElseGet(() -> getApartmentDetailFromDatabase(id));
    }

    public ApartmentDetailVo getApartmentDetailFromDatabase(Long id) {
        ApartmentInfo apartmentInfo = apartmentInfoMapper.selectById(id);
        ApartmentDetailVo apartmentDetailVo = ApartmentDetailVo.builder()
                .graphVoList(graphInfoMapper.selectListByItemTypeAndId(ItemType.APARTMENT, id))
                .labelInfoList(labelInfoMapper.selectListByApartmentId(id))
                .facilityInfoList(facilityInfoMapper.selectListByApartmentId(id))
                .minRent(roomInfoMapper.selectMinRentByApartmentId(id))
                .build();
        BeanUtils.copyProperties(apartmentInfo, apartmentDetailVo);
        stringObjectRedisTemplate.opsForValue().set(
                RedisConstant.APP_APARTMENT_PREFIX,
                apartmentDetailVo,
                RedisConstant.DEFAULT_EXPIRE_TIME,
                TimeUnit.SECONDS
        );
        return apartmentDetailVo;
    }

}
