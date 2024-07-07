package com.jxh.lease.web.admin.mapper;

import com.jxh.lease.model.entity.FeeValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxh.lease.web.admin.vo.fee.FeeValueVo;

import java.util.List;

public interface FeeValueMapper extends BaseMapper<FeeValue> {

    List<FeeValueVo> selectListByApartmentId(Long id);
}