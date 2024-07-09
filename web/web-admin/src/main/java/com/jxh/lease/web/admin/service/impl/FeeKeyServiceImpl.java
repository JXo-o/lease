package com.jxh.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jxh.lease.model.entity.FeeKey;
import com.jxh.lease.model.entity.FeeValue;
import com.jxh.lease.web.admin.mapper.FeeKeyMapper;
import com.jxh.lease.web.admin.mapper.FeeValueMapper;
import com.jxh.lease.web.admin.service.FeeKeyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.web.admin.vo.fee.FeeKeyVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeeKeyServiceImpl extends ServiceImpl<FeeKeyMapper, FeeKey>
    implements FeeKeyService{

    private final FeeKeyMapper feeKeyMapper;
    private final FeeValueMapper feeValueMapper;

    public FeeKeyServiceImpl(FeeKeyMapper feeKeyMapper, FeeValueMapper feeValueMapper) {
        this.feeKeyMapper = feeKeyMapper;
        this.feeValueMapper = feeValueMapper;
    }

    @Override
    public List<FeeKeyVo> listFeeInfo() {
        return feeKeyMapper.listFeeInfo();
    }

    /**
     * 根据杂费Id删除杂费及对应杂费值
     * @param feeKeyId 杂费Id
     */
    @Override
    @Transactional
    public void removeFeeKeyById(Long feeKeyId) {
        feeKeyMapper.deleteById(feeKeyId);
        LambdaQueryWrapper<FeeValue> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(FeeValue::getFeeKeyId, feeKeyId);
        List<FeeValue> feeValues = feeValueMapper.selectList(lambdaQueryWrapper);
        List<Long> idList = feeValues.stream()
                .map(FeeValue::getId)
                .toList();
        feeValueMapper.deleteByIds(idList);
    }
}
