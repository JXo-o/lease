package com.jxh.lease.web.admin.mapper;

import com.jxh.lease.model.entity.AttrKey;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxh.lease.web.admin.vo.attr.AttrKeyVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface AttrKeyMapper extends BaseMapper<AttrKey> {

    List<AttrKeyVo> listAttrInfo();
}