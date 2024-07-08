package com.jxh.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxh.lease.model.entity.RoomInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxh.lease.web.admin.vo.room.RoomItemVo;
import com.jxh.lease.web.admin.vo.room.RoomQueryVo;

public interface RoomInfoMapper extends BaseMapper<RoomInfo> {

    IPage<RoomItemVo> pageApartmentItemByQuery(IPage<RoomItemVo> roomItemVoPage, RoomQueryVo queryVo);
}