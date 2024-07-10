package com.jxh.lease.web.app.mapper;

import com.jxh.lease.model.entity.LeaseAgreement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxh.lease.web.app.vo.agreement.AgreementItemVo;

import java.util.List;

public interface LeaseAgreementMapper extends BaseMapper<LeaseAgreement> {

    List<AgreementItemVo> listItemByPhone(String phone);
}
