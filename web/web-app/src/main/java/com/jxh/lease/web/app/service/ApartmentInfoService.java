package com.jxh.lease.web.app.service;

import com.jxh.lease.model.entity.ApartmentInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxh.lease.web.app.vo.apartment.ApartmentDetailVo;
import com.jxh.lease.web.app.vo.apartment.ApartmentItemVo;

public interface ApartmentInfoService extends IService<ApartmentInfo> {
    ApartmentItemVo selectApartmentItemVoById(Long apartmentId);

    ApartmentDetailVo getApartmentDetailById(Long id);
}
