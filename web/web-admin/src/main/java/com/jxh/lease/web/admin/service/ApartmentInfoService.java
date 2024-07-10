package com.jxh.lease.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxh.lease.model.entity.ApartmentInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxh.lease.web.admin.vo.apartment.ApartmentDetailVo;
import com.jxh.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.jxh.lease.web.admin.vo.apartment.ApartmentQueryVo;
import com.jxh.lease.web.admin.vo.apartment.ApartmentSubmitVo;

public interface ApartmentInfoService extends IService<ApartmentInfo> {

    void saveOrUpdateApartment(ApartmentSubmitVo apartmentSubmitVo);

    IPage<ApartmentItemVo> pageApartmentItemByQuery(IPage<ApartmentItemVo> page, ApartmentQueryVo queryVo);

    ApartmentDetailVo getDetailById(Long id);

    void removeApartmentById(Long id);
}
