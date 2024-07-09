package com.jxh.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jxh.lease.model.entity.*;
import com.jxh.lease.model.enums.ItemType;
import com.jxh.lease.web.admin.mapper.*;
import com.jxh.lease.web.admin.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.web.admin.vo.graph.GraphVo;
import com.jxh.lease.web.admin.vo.room.RoomDetailVo;
import com.jxh.lease.web.admin.vo.room.RoomItemVo;
import com.jxh.lease.web.admin.vo.room.RoomQueryVo;
import com.jxh.lease.web.admin.vo.room.RoomSubmitVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo>
        implements RoomInfoService {

    private final ApartmentInfoMapper apartmentInfoMapper;
    private final RoomInfoMapper roomInfoMapper;
    private final GraphInfoMapper graphInfoMapper;
    private final AttrValueMapper attrValueMapper;
    private final FacilityInfoMapper facilityInfoMapper;
    private final LabelInfoMapper labelInfoMapper;
    private final PaymentTypeMapper paymentTypeMapper;
    private final LeaseTermMapper leaseTermMapper;
    private final GraphInfoService graphInfoService;
    private final RoomAttrValueService roomAttrValueService;
    private final RoomFacilityService roomFacilityService;
    private final RoomLabelService roomLabelService;
    private final RoomLeaseTermService roomLeaseTermService;
    private final RoomPaymentTypeService roomPaymentTypeService;

    public RoomInfoServiceImpl(
            ApartmentInfoMapper apartmentInfoMapper,
            LabelInfoMapper labelInfoMapper,
            RoomInfoMapper roomInfoMapper,
            GraphInfoMapper graphInfoMapper,
            AttrValueMapper attrValueMapper,
            FacilityInfoMapper facilityInfoMapper,
            PaymentTypeMapper paymentTypeMapper,
            LeaseTermMapper leaseTermMapper,
            GraphInfoService graphInfoService,
            RoomAttrValueService roomAttrValueService,
            RoomFacilityService roomFacilityService,
            RoomLabelService roomLabelService,
            RoomLeaseTermService roomLeaseTermService,
            RoomPaymentTypeService roomPaymentTypeService
    ) {
        this.apartmentInfoMapper = apartmentInfoMapper;
        this.labelInfoMapper = labelInfoMapper;
        this.roomInfoMapper = roomInfoMapper;
        this.graphInfoMapper = graphInfoMapper;
        this.attrValueMapper = attrValueMapper;
        this.facilityInfoMapper = facilityInfoMapper;
        this.paymentTypeMapper = paymentTypeMapper;
        this.leaseTermMapper = leaseTermMapper;
        this.graphInfoService = graphInfoService;
        this.roomAttrValueService = roomAttrValueService;
        this.roomFacilityService = roomFacilityService;
        this.roomLabelService = roomLabelService;
        this.roomLeaseTermService = roomLeaseTermService;
        this.roomPaymentTypeService = roomPaymentTypeService;
    }

    /**
     * 保存或更新房间的信息
     * 逻辑：更新时先逻辑删除所有配套信息，再新加
     * @param roomSubmitVo 房间的信息
     */
    @Override
    @Transactional
    public void saveOrUpdateRoom(RoomSubmitVo roomSubmitVo) {

        boolean isUpdate = roomSubmitVo.getId() != null;
        super.saveOrUpdate(roomSubmitVo);

        if (isUpdate) {
            removeRelatedInfo(roomSubmitVo.getId());
        }

        List<GraphVo> graphVoList = roomSubmitVo.getGraphVoList();
        if (!CollectionUtils.isEmpty(graphVoList)) {
            List<GraphInfo> graphInfoList = graphVoList.stream()
                    .map(graphVo -> GraphInfo.builder()
                            .itemType(ItemType.ROOM)
                            .itemId(roomSubmitVo.getId())
                            .name(graphVo.getName())
                            .url(graphVo.getUrl())
                            .build())
                    .toList();
            graphInfoService.saveBatch(graphInfoList);
        }

        List<Long> attrValueIds = roomSubmitVo.getAttrValueIds();
        if (!CollectionUtils.isEmpty(attrValueIds)) {
            List<RoomAttrValue> roomAttrValueList = attrValueIds.stream()
                    .map(attrValueId -> RoomAttrValue.builder()
                            .roomId(roomSubmitVo.getId())
                            .attrValueId(attrValueId)
                            .build())
                    .toList();
            roomAttrValueService.saveBatch(roomAttrValueList);
        }

        List<Long> facilityInfoIds = roomSubmitVo.getFacilityInfoIds();
        if (!CollectionUtils.isEmpty(facilityInfoIds)) {
            List<RoomFacility> roomFacilityList = facilityInfoIds.stream()
                    .map(facilityId -> RoomFacility.builder()
                            .roomId(roomSubmitVo.getId())
                            .facilityId(facilityId)
                            .build())
                    .toList();
            roomFacilityService.saveBatch(roomFacilityList);
        }

        List<Long> labelInfoIds = roomSubmitVo.getLabelInfoIds();
        if (!CollectionUtils.isEmpty(labelInfoIds)) {
            List<RoomLabel> roomLabelList = labelInfoIds.stream()
                    .map(labelId -> RoomLabel.builder()
                            .roomId(roomSubmitVo.getId())
                            .labelId(labelId)
                            .build())
                    .toList();
            roomLabelService.saveBatch(roomLabelList);
        }

        List<Long> paymentTypeIds = roomSubmitVo.getPaymentTypeIds();
        if (!CollectionUtils.isEmpty(paymentTypeIds)) {
            List<RoomPaymentType> roomPaymentTypeList = paymentTypeIds.stream()
                    .map(paymentTypeId -> RoomPaymentType.builder()
                            .roomId(roomSubmitVo.getId())
                            .paymentTypeId(paymentTypeId)
                            .build())
                    .toList();
            roomPaymentTypeService.saveBatch(roomPaymentTypeList);
        }

        List<Long> leaseTermIds = roomSubmitVo.getLeaseTermIds();
        if (!CollectionUtils.isEmpty(leaseTermIds)) {
            List<RoomLeaseTerm> roomLeaseTermList = leaseTermIds.stream()
                    .map(leaseTermId -> RoomLeaseTerm.builder()
                            .roomId(roomSubmitVo.getId())
                            .leaseTermId(leaseTermId)
                            .build())
                    .toList();
            roomLeaseTermService.saveBatch(roomLeaseTermList);
        }

    }

    @Override
    public IPage<RoomItemVo> pageRoomItemByQuery(IPage<RoomItemVo> roomItemVoPage, RoomQueryVo queryVo) {
        return roomInfoMapper.pageApartmentItemByQuery(roomItemVoPage, queryVo);
    }

    @Override
    public RoomDetailVo getRoomDetailById(Long id) {

        RoomDetailVo roomDetailVo = new RoomDetailVo();
        BeanUtils.copyProperties(roomInfoMapper.selectById(id), roomDetailVo);
        roomDetailVo.setApartmentInfo(apartmentInfoMapper.selectById(roomDetailVo.getApartmentId()));
        roomDetailVo.setGraphVoList(graphInfoMapper.selectListByItemTypeAndId(ItemType.ROOM, id));
        roomDetailVo.setAttrValueVoList(attrValueMapper.selectListByRoomId(id));
        roomDetailVo.setFacilityInfoList(facilityInfoMapper.selectListByRoomId(id));
        roomDetailVo.setLabelInfoList(labelInfoMapper.selectListByRoomId(id));
        roomDetailVo.setPaymentTypeList(paymentTypeMapper.selectListByRoomId(id));
        roomDetailVo.setLeaseTermList(leaseTermMapper.selectListByRoomId(id));

        return roomDetailVo;
    }

    @Override
    public void removeRoomById(Long id) {
        super.removeById(id);
        removeRelatedInfo(id);
    }

    private void removeRelatedInfo(Long id) {
        graphInfoService.lambdaUpdate()
                .eq(GraphInfo::getItemType, ItemType.ROOM)
                .eq(GraphInfo::getItemId, id)
                .remove();
        roomAttrValueService.lambdaUpdate()
                .eq(RoomAttrValue::getRoomId, id)
                .remove();
        roomFacilityService.lambdaUpdate()
                .eq(RoomFacility::getRoomId, id)
                .remove();
        roomLabelService.lambdaUpdate()
                .eq(RoomLabel::getRoomId, id)
                .remove();
        roomPaymentTypeService.lambdaUpdate()
                .eq(RoomPaymentType::getRoomId, id)
                .remove();
        roomLabelService.lambdaUpdate()
                .eq(RoomLabel::getRoomId, id)
                .remove();
    }
}
