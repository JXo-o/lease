package com.jxh.lease.web.app.service.impl;

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
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ApartmentInfoServiceImpl extends ServiceImpl<ApartmentInfoMapper, ApartmentInfo>
        implements ApartmentInfoService {

    private final ApartmentInfoMapper apartmentInfoMapper;
    private final LabelInfoMapper labelInfoMapper;
    private final GraphInfoMapper graphInfoMapper;
    private final RoomInfoMapper roomInfoMapper;
    private final FacilityInfoMapper facilityInfoMapper;

    public ApartmentInfoServiceImpl(
            ApartmentInfoMapper apartmentInfoMapper,
            LabelInfoMapper labelInfoMapper,
            GraphInfoMapper graphInfoMapper,
            RoomInfoMapper roomInfoMapper,
            FacilityInfoMapper facilityInfoMapper
    ) {
        this.apartmentInfoMapper = apartmentInfoMapper;
        this.labelInfoMapper = labelInfoMapper;
        this.graphInfoMapper = graphInfoMapper;
        this.roomInfoMapper = roomInfoMapper;
        this.facilityInfoMapper = facilityInfoMapper;
    }

    @Override
    public ApartmentItemVo selectApartmentItemVoById(Long apartmentId) {

        ApartmentInfo apartmentInfo = apartmentInfoMapper.selectById(apartmentId);
        ApartmentItemVo apartmentItemVo = ApartmentItemVo.builder()
                .labelInfoList(labelInfoMapper.selectListByApartmentId(apartmentId))
                .graphVoList(graphInfoMapper.selectListByItemTypeAndId(ItemType.APARTMENT, apartmentId))
                .minRent(roomInfoMapper.selectMinRentByApartmentId(apartmentId))
                .build();
        BeanUtils.copyProperties(apartmentInfo, apartmentItemVo);

        return apartmentItemVo;
    }

    @Override
    public ApartmentDetailVo getApartmentDetailById(Long id) {

        ApartmentInfo apartmentInfo = apartmentInfoMapper.selectById(id);
        ApartmentDetailVo apartmentDetailVo = ApartmentDetailVo.builder()
                .graphVoList(graphInfoMapper.selectListByItemTypeAndId(ItemType.APARTMENT, id))
                .labelInfoList(labelInfoMapper.selectListByApartmentId(id))
                .facilityInfoList(facilityInfoMapper.selectListByApartmentId(id))
                .minRent(roomInfoMapper.selectMinRentByApartmentId(id))
                .build();
        BeanUtils.copyProperties(apartmentInfo, apartmentDetailVo);

        return apartmentDetailVo;
    }
}
