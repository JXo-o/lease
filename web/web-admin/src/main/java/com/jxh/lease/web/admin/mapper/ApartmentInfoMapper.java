package com.jxh.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxh.lease.model.entity.ApartmentInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxh.lease.web.admin.vo.apartment.ApartmentDetailVo;
import com.jxh.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.jxh.lease.web.admin.vo.apartment.ApartmentQueryVo;

public interface ApartmentInfoMapper extends BaseMapper<ApartmentInfo> {

    IPage<ApartmentItemVo> pageApartmentItemByQuery(IPage<ApartmentItemVo> page, ApartmentQueryVo queryVo);

}