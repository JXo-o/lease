package com.jxh.lease.web.admin.mapper;

import com.jxh.lease.model.entity.GraphInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxh.lease.model.enums.ItemType;
import com.jxh.lease.web.admin.vo.graph.GraphVo;

import java.util.List;

public interface GraphInfoMapper extends BaseMapper<GraphInfo> {

    List<GraphVo> selectListByItemTypeAndId(ItemType itemType, Long id);
}