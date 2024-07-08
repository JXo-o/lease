package com.jxh.lease.web.admin.mapper;

import com.jxh.lease.model.entity.AttrValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxh.lease.web.admin.vo.attr.AttrValueVo;

import java.util.List;

public interface AttrValueMapper extends BaseMapper<AttrValue> {

    List<AttrValueVo> selectListByRoomId(Long id);
}