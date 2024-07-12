package com.jxh.lease.web.app.service.impl;

import com.jxh.lease.common.redis.RedisConstant;
import com.jxh.lease.model.entity.*;
import com.jxh.lease.model.enums.ItemType;
import com.jxh.lease.web.app.mapper.*;
import com.jxh.lease.web.app.service.LeaseAgreementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.web.app.vo.agreement.AgreementDetailVo;
import com.jxh.lease.web.app.vo.agreement.AgreementItemVo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class LeaseAgreementServiceImpl extends ServiceImpl<LeaseAgreementMapper, LeaseAgreement>
        implements LeaseAgreementService {

    private final LeaseAgreementMapper leaseAgreementMapper;
    private final ApartmentInfoMapper apartmentInfoMapper;
    private final RoomInfoMapper roomInfoMapper;
    private final GraphInfoMapper graphInfoMapper;
    private final PaymentTypeMapper paymentTypeMapper;
    private final LeaseTermMapper leaseTermMapper;
    private final RedisTemplate<String, Object> stringObjectRedisTemplate;

    public LeaseAgreementServiceImpl(
            LeaseAgreementMapper leaseAgreementMapper,
            ApartmentInfoMapper apartmentInfoMapper,
            RoomInfoMapper roomInfoMapper,
            GraphInfoMapper graphInfoMapper,
            PaymentTypeMapper paymentTypeMapper,
            LeaseTermMapper leaseTermMapper,
            RedisTemplate<String, Object> stringObjectRedisTemplate
    ) {
        this.leaseAgreementMapper = leaseAgreementMapper;
        this.apartmentInfoMapper = apartmentInfoMapper;
        this.roomInfoMapper = roomInfoMapper;
        this.graphInfoMapper = graphInfoMapper;
        this.paymentTypeMapper = paymentTypeMapper;
        this.leaseTermMapper = leaseTermMapper;
        this.stringObjectRedisTemplate = stringObjectRedisTemplate;
    }

    @Override
    public List<AgreementItemVo> listItemByPhone(String phone) {
        return leaseAgreementMapper.listItemByPhone(phone);
    }

    @Override
    public AgreementDetailVo getDetailById(Long id) {
        return Optional.ofNullable(
                (AgreementDetailVo) stringObjectRedisTemplate.opsForValue().get(RedisConstant.APP_AGREEMENT_PREFIX + id)
        ).orElseGet(() -> getAgreementDetailFromDatabase(id));
    }

    public AgreementDetailVo getAgreementDetailFromDatabase(Long id) {
        LeaseAgreement leaseAgreement = leaseAgreementMapper.selectById(id);
        AgreementDetailVo agreementDetailVo = AgreementDetailVo.builder()
                .apartmentName(apartmentInfoMapper.selectById(leaseAgreement.getApartmentId()).getName())
                .roomNumber(roomInfoMapper.selectById(leaseAgreement.getRoomId()).getRoomNumber())
                .apartmentGraphVoList(graphInfoMapper.selectListByItemTypeAndId(ItemType.APARTMENT, leaseAgreement.getApartmentId()))
                .roomGraphVoList(graphInfoMapper.selectListByItemTypeAndId(ItemType.ROOM, leaseAgreement.getRoomId()))
                .paymentTypeName(paymentTypeMapper.selectById(leaseAgreement.getPaymentTypeId()).getName())
                .leaseTermMonthCount(leaseTermMapper.selectById(leaseAgreement.getLeaseTermId()).getMonthCount())
                .leaseTermUnit(leaseTermMapper.selectById(leaseAgreement.getLeaseTermId()).getUnit())
                .build();
        BeanUtils.copyProperties(leaseAgreement, agreementDetailVo);
        stringObjectRedisTemplate.opsForValue().set(
                RedisConstant.APP_AGREEMENT_PREFIX + id,
                agreementDetailVo,
                RedisConstant.DEFAULT_EXPIRE_TIME,
                TimeUnit.SECONDS
        );
        return agreementDetailVo;
    }

}
