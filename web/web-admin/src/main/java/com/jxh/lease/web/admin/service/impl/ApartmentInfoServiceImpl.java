package com.jxh.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.jxh.lease.common.exception.LeaseException;
import com.jxh.lease.common.result.ResultCodeEnum;
import com.jxh.lease.model.entity.*;
import com.jxh.lease.model.enums.ItemType;
import com.jxh.lease.web.admin.mapper.*;
import com.jxh.lease.web.admin.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.web.admin.vo.apartment.ApartmentDetailVo;
import com.jxh.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.jxh.lease.web.admin.vo.apartment.ApartmentQueryVo;
import com.jxh.lease.web.admin.vo.apartment.ApartmentSubmitVo;
import com.jxh.lease.web.admin.vo.graph.GraphVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApartmentInfoServiceImpl extends ServiceImpl<ApartmentInfoMapper, ApartmentInfo>
        implements ApartmentInfoService {

    private final ApartmentInfoMapper apartmentInfoMapper;
    private final GraphInfoMapper graphInfoMapper;
    private final LabelInfoMapper labelInfoMapper;
    private final FacilityInfoMapper facilityInfoMapper;
    private final FeeValueMapper feeValueMapper;
    private final RoomInfoMapper roomInfoMapper;
    private final ApartmentFacilityService apartmentFacilityService;
    private final ApartmentFeeValueService apartmentFeeValueService;
    private final ApartmentLabelService apartmentLabelService;
    private final GraphInfoService graphInfoService;

    public ApartmentInfoServiceImpl(
            ApartmentInfoMapper apartmentInfoMapper,
            GraphInfoMapper graphInfoMapper,
            LabelInfoMapper labelInfoMapper,
            FacilityInfoMapper facilityInfoMapper,
            FeeValueMapper feeValueMapper,
            RoomInfoMapper roomInfoMapper,
            ApartmentFacilityService apartmentFacilityService,
            ApartmentFeeValueService apartmentFeeValueService,
            ApartmentLabelService apartmentLabelService,
            GraphInfoService graphInfoService
    ) {
        this.apartmentInfoMapper = apartmentInfoMapper;
        this.graphInfoMapper = graphInfoMapper;
        this.labelInfoMapper = labelInfoMapper;
        this.facilityInfoMapper = facilityInfoMapper;
        this.feeValueMapper = feeValueMapper;
        this.roomInfoMapper = roomInfoMapper;
        this.apartmentFacilityService = apartmentFacilityService;
        this.apartmentFeeValueService = apartmentFeeValueService;
        this.apartmentLabelService = apartmentLabelService;
        this.graphInfoService = graphInfoService;
    }

    @Override
    @Transactional
    public void saveOrUpdateApartment(ApartmentSubmitVo apartmentSubmitVo) {

        boolean isUpdate = apartmentSubmitVo.getId() != null;
        super.saveOrUpdate(apartmentSubmitVo);

        if (isUpdate) {
            this.removeRelatedInfo(apartmentSubmitVo.getId());
        }

        List<GraphVo> graphVoList = apartmentSubmitVo.getGraphVoList();
        if (!CollectionUtils.isEmpty(graphVoList)) {
            List<GraphInfo> graphInfoList = graphVoList.stream()
                    .map(graphVo -> GraphInfo.builder()
                            .itemType(ItemType.APARTMENT)
                            .itemId(apartmentSubmitVo.getId())
                            .name(graphVo.getName())
                            .url(graphVo.getUrl())
                            .build())
                    .toList();
            graphInfoService.saveBatch(graphInfoList);
        }

        List<Long> facilityInfoIds = apartmentSubmitVo.getFacilityInfoIds();
        if (!CollectionUtils.isEmpty(facilityInfoIds)) {
            List<ApartmentFacility> facilityList = facilityInfoIds.stream()
                    .map(facilityId -> ApartmentFacility.builder()
                            .apartmentId(apartmentSubmitVo.getId())
                            .facilityId(facilityId)
                            .build())
                    .toList();
            apartmentFacilityService.saveBatch(facilityList);
        }

        List<Long> labelIds = apartmentSubmitVo.getLabelIds();
        if (!CollectionUtils.isEmpty(labelIds)) {
            List<ApartmentLabel> apartmentLabelList = labelIds.stream()
                    .map(labelId -> ApartmentLabel.builder()
                            .apartmentId(apartmentSubmitVo.getId())
                            .labelId(labelId)
                            .build())
                    .toList();
            apartmentLabelService.saveBatch(apartmentLabelList);
        }

        List<Long> feeValueIds = apartmentSubmitVo.getFeeValueIds();
        if (!CollectionUtils.isEmpty(feeValueIds)) {
            List<ApartmentFeeValue> apartmentFeeValueList = feeValueIds.stream()
                    .map(feeValueId -> ApartmentFeeValue.builder()
                            .apartmentId(apartmentSubmitVo.getId())
                            .feeValueId(feeValueId)
                            .build())
                    .toList();
            apartmentFeeValueService.saveBatch(apartmentFeeValueList);
        }

    }

    @Override
    public IPage<ApartmentItemVo> pageApartmentItemByQuery(IPage<ApartmentItemVo> page, ApartmentQueryVo queryVo) {
        return apartmentInfoMapper.pageApartmentItemByQuery(page, queryVo);
    }

    /**
     * 根据id查详情，包括公寓信息、设施、费用、标签、图片
     * 这里选择分开查询并组装，而不是使用联表查询
     * @param id 公寓id
     * @return 公寓详情vo
     */
    @Override
    public ApartmentDetailVo getDetailById(Long id) {

        ApartmentDetailVo apartmentDetailVo = new ApartmentDetailVo();
        BeanUtils.copyProperties(apartmentInfoMapper.selectById(id), apartmentDetailVo);
        apartmentDetailVo.setGraphVoList(graphInfoMapper.selectListByItemTypeAndId(ItemType.APARTMENT, id));
        apartmentDetailVo.setLabelInfoList(labelInfoMapper.selectListByApartmentId(id));
        apartmentDetailVo.setFacilityInfoList(facilityInfoMapper.selectListByApartmentId(id));
        apartmentDetailVo.setFeeValueVoList(feeValueMapper.selectListByApartmentId(id));

        return apartmentDetailVo;

    }

    @Override
    @Transactional
    public void removeApartmentById(Long id) {

        LambdaQueryWrapper<RoomInfo> roomInfoQueryWrapper = new LambdaQueryWrapper<>();
        roomInfoQueryWrapper.eq(RoomInfo::getApartmentId, id);
        if (roomInfoMapper.selectCount(roomInfoQueryWrapper) > 0) {
            throw new LeaseException(ResultCodeEnum.ADMIN_APARTMENT_DELETE_ERROR);
        }

        super.removeById(id);
        this.removeRelatedInfo(id);
    }

    @Transactional
    public void removeRelatedInfo(Long id) {
        graphInfoService.lambdaUpdate()
                .eq(GraphInfo::getItemType, ItemType.APARTMENT)
                .eq(GraphInfo::getItemId, id)
                .remove();
        apartmentFacilityService.lambdaUpdate()
                .eq(ApartmentFacility::getApartmentId, id)
                .remove();
        apartmentFeeValueService.lambdaUpdate()
                .eq(ApartmentFeeValue::getApartmentId, id)
                .remove();
        apartmentLabelService.lambdaUpdate()
                .eq(ApartmentLabel::getApartmentId, id)
                .remove();
    }

}
