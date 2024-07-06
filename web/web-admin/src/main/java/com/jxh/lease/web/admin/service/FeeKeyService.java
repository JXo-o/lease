package com.jxh.lease.web.admin.service;

import com.jxh.lease.model.entity.FeeKey;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxh.lease.web.admin.vo.fee.FeeKeyVo;

import java.util.List;

public interface FeeKeyService extends IService<FeeKey> {

    List<FeeKeyVo> listFeeInfo();

    void removeFeeKeyById(Long feeKeyId);
}
