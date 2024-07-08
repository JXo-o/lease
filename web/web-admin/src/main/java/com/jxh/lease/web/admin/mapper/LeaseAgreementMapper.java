package com.jxh.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxh.lease.model.entity.LeaseAgreement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxh.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.jxh.lease.web.admin.vo.agreement.AgreementVo;

public interface LeaseAgreementMapper extends BaseMapper<LeaseAgreement> {

    IPage<AgreementVo> pageAgreementByQuery(IPage<AgreementVo> page, AgreementQueryVo queryVo);
}