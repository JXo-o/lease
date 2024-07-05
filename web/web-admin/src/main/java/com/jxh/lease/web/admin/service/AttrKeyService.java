package com.jxh.lease.web.admin.service;

import com.jxh.lease.model.entity.AttrKey;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jxh.lease.web.admin.vo.attr.AttrKeyVo;

import java.util.List;

public interface AttrKeyService extends IService<AttrKey> {

    List<AttrKeyVo> listAttrInfo();

    void removeAttrKeyById(Long attrKeyId);
}
