package com.jxh.lease.web.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxh.lease.model.entity.RoomInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxh.lease.web.admin.vo.room.RoomDetailVo;
import com.jxh.lease.web.admin.vo.room.RoomItemVo;
import com.jxh.lease.web.admin.vo.room.RoomQueryVo;
import com.jxh.lease.web.admin.vo.room.RoomSubmitVo;

public interface RoomInfoService extends IService<RoomInfo> {

    void saveOrUpdateRoom(RoomSubmitVo roomSubmitVo);

    IPage<RoomItemVo> pageRoomItemByQuery(IPage<RoomItemVo> roomItemVoPage, RoomQueryVo queryVo);

    RoomDetailVo getRoomDetailById(Long id);

    void removeRoomById(Long id);

}
