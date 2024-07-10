package com.jxh.lease.web.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxh.lease.model.entity.RoomInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxh.lease.web.app.vo.room.RoomDetailVo;
import com.jxh.lease.web.app.vo.room.RoomItemVo;
import com.jxh.lease.web.app.vo.room.RoomQueryVo;

public interface RoomInfoService extends IService<RoomInfo> {
    IPage<RoomItemVo> pageRoomItemByQuery(IPage<RoomItemVo> page, RoomQueryVo queryVo);

    RoomDetailVo getDetailById(Long id);

    IPage<RoomItemVo> pageItemByApartmentId(IPage<RoomItemVo> page, Long id);
}
