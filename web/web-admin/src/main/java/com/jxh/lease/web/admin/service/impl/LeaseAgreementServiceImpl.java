package com.jxh.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxh.lease.model.entity.ApartmentInfo;
import com.jxh.lease.model.entity.LeaseAgreement;
import com.jxh.lease.model.entity.LeaseTerm;
import com.jxh.lease.model.entity.RoomInfo;
import com.jxh.lease.web.admin.mapper.*;
import com.jxh.lease.web.admin.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.jxh.lease.web.admin.vo.agreement.AgreementVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaseAgreementServiceImpl extends ServiceImpl<LeaseAgreementMapper, LeaseAgreement>
        implements LeaseAgreementService {

    private final LeaseAgreementMapper leaseAgreementMapper;
    private final ApartmentInfoMapper apartmentInfoMapper;
    private final RoomInfoMapper roomInfoMapper;
    private final LeaseTermMapper leaseTermMapper;
    private final PaymentTypeMapper paymentTypeMapper;

    public LeaseAgreementServiceImpl(
            LeaseAgreementMapper leaseAgreementMapper,
            ApartmentInfoMapper apartmentInfoMapper,
            RoomInfoMapper roomInfoMapper, LeaseTermMapper leaseTermMapper,
            PaymentTypeMapper paymentTypeMapper
    ) {
        this.leaseAgreementMapper = leaseAgreementMapper;
        this.apartmentInfoMapper = apartmentInfoMapper;
        this.roomInfoMapper = roomInfoMapper;
        this.leaseTermMapper = leaseTermMapper;
        this.paymentTypeMapper = paymentTypeMapper;
    }

    @Override
    public AgreementVo getAgreementById(Long id) {

        AgreementVo agreementVo = new AgreementVo();
        BeanUtils.copyProperties(leaseAgreementMapper.selectById(id), agreementVo);
        agreementVo.setApartmentInfo(apartmentInfoMapper.selectById(agreementVo.getApartmentId()));
        agreementVo.setRoomInfo(roomInfoMapper.selectById(agreementVo.getRoomId()));
        agreementVo.setLeaseTerm(leaseTermMapper.selectById(agreementVo.getLeaseTermId()));
        agreementVo.setPaymentType(paymentTypeMapper.selectById(agreementVo.getPaymentTypeId()));

        return agreementVo;

    }

    @Override
    public IPage<AgreementVo> pageAgreementByQuery(IPage<AgreementVo> page, AgreementQueryVo queryVo) {
        return leaseAgreementMapper.pageAgreementByQuery(page, queryVo);
    }
}




