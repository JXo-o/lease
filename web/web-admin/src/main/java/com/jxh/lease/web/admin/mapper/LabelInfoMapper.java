package com.jxh.lease.web.admin.mapper;

import com.jxh.lease.model.entity.LabelInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface LabelInfoMapper extends BaseMapper<LabelInfo> {

    List<LabelInfo> selectListByApartmentId(Long id);

    List<LabelInfo> selectListByRoomId(Long id);
}