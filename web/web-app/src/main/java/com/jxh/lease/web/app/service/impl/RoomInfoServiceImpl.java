package com.jxh.lease.web.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.common.login.LoginUserHolder;
import com.jxh.lease.model.entity.RoomInfo;
import com.jxh.lease.model.enums.ItemType;
import com.jxh.lease.web.app.mapper.*;
import com.jxh.lease.web.app.service.ApartmentInfoService;
import com.jxh.lease.web.app.service.BrowsingHistoryService;
import com.jxh.lease.web.app.service.RoomInfoService;
import com.jxh.lease.web.app.vo.room.RoomDetailVo;
import com.jxh.lease.web.app.vo.room.RoomItemVo;
import com.jxh.lease.web.app.vo.room.RoomQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo>
        implements RoomInfoService {

    private final RoomInfoMapper roomInfoMapper;
    private final GraphInfoMapper graphInfoMapper;
    private final LeaseTermMapper leaseTermMapper;
    private final FacilityInfoMapper facilityInfoMapper;
    private final LabelInfoMapper labelInfoMapper;
    private final PaymentTypeMapper paymentTypeMapper;
    private final AttrValueMapper attrValueMapper;
    private final FeeValueMapper feeValueMapper;
    private final ApartmentInfoService apartmentInfoService;
    private final BrowsingHistoryService browsingHistoryService;

    public RoomInfoServiceImpl(
            RoomInfoMapper roomInfoMapper,
            GraphInfoMapper graphInfoMapper,
            LeaseTermMapper leaseTermMapper,
            FacilityInfoMapper facilityInfoMapper,
            LabelInfoMapper labelInfoMapper,
            PaymentTypeMapper paymentTypeMapper,
            AttrValueMapper attrValueMapper,
            FeeValueMapper feeValueMapper,
            ApartmentInfoService apartmentInfoService,
            BrowsingHistoryService browsingHistoryService
    ) {
        this.roomInfoMapper = roomInfoMapper;
        this.graphInfoMapper = graphInfoMapper;
        this.leaseTermMapper = leaseTermMapper;
        this.facilityInfoMapper = facilityInfoMapper;
        this.labelInfoMapper = labelInfoMapper;
        this.paymentTypeMapper = paymentTypeMapper;
        this.attrValueMapper = attrValueMapper;
        this.feeValueMapper = feeValueMapper;
        this.apartmentInfoService = apartmentInfoService;
        this.browsingHistoryService = browsingHistoryService;
    }

    @Override
    public IPage<RoomItemVo> pageRoomItemByQuery(IPage<RoomItemVo> page, RoomQueryVo queryVo) {
        return roomInfoMapper.pageRoomItemByQuery(page, queryVo);
    }

    @Override
    public RoomDetailVo getDetailById(Long id) {

        RoomInfo roomInfo = roomInfoMapper.selectById(id);
        RoomDetailVo roomDetailVo = RoomDetailVo.builder()
                .graphVoList(graphInfoMapper.selectListByItemTypeAndId(ItemType.ROOM, id))
                .leaseTermList(leaseTermMapper.selectListByRoomId(id))
                .facilityInfoList(facilityInfoMapper.selectListByRoomId(id))
                .labelInfoList(labelInfoMapper.selectListByRoomId(id))
                .paymentTypeList(paymentTypeMapper.selectListByRoomId(id))
                .attrValueVoList(attrValueMapper.selectListByRoomId(id))
                .feeValueVoList(feeValueMapper.selectListByApartmentId(roomInfo.getApartmentId()))
                .apartmentItemVo(apartmentInfoService.selectApartmentItemVoById(roomInfo.getApartmentId()))
                .build();
        BeanUtils.copyProperties(roomInfo, roomDetailVo);
        browsingHistoryService.saveHistory(LoginUserHolder.getLoginUser().getUserId(), id);

        return roomDetailVo;
    }

    @Override
    public IPage<RoomItemVo> pageItemByApartmentId(IPage<RoomItemVo> page, Long id) {
        return roomInfoMapper.pageItemByApartmentId(page, id);
    }
}
