package com.jxh.lease.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxh.lease.model.entity.LeaseAgreement;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxh.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.jxh.lease.web.admin.vo.agreement.AgreementVo;

public interface LeaseAgreementService extends IService<LeaseAgreement> {

    AgreementVo getAgreementById(Long id);

    IPage<AgreementVo> pageAgreementByQuery(IPage<AgreementVo> page, AgreementQueryVo queryVo);
}
