package com.jxh.lease.web.app.service.impl;

import com.jxh.lease.model.entity.LeaseAgreement;
import com.jxh.lease.web.app.mapper.LeaseAgreementMapper;
import com.jxh.lease.web.app.service.LeaseAgreementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class LeaseAgreementServiceImpl extends ServiceImpl<LeaseAgreementMapper, LeaseAgreement>
        implements LeaseAgreementService {

}
