package com.jxh.lease.web.app.service;

import com.jxh.lease.model.entity.LeaseAgreement;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxh.lease.web.app.vo.agreement.AgreementDetailVo;
import com.jxh.lease.web.app.vo.agreement.AgreementItemVo;

import java.util.List;

public interface LeaseAgreementService extends IService<LeaseAgreement> {
    List<AgreementItemVo> listItemByPhone(String phone);

    AgreementDetailVo getDetailById(Long id);
}
