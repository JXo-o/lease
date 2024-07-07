package com.jxh.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.jxh.lease.model.entity.AttrKey;
import com.jxh.lease.model.entity.AttrValue;
import com.jxh.lease.web.admin.mapper.AttrKeyMapper;
import com.jxh.lease.web.admin.mapper.AttrValueMapper;
import com.jxh.lease.web.admin.service.AttrKeyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxh.lease.web.admin.vo.attr.AttrKeyVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttrKeyServiceImpl extends ServiceImpl<AttrKeyMapper, AttrKey>
        implements AttrKeyService{

    private final AttrKeyMapper attrKeyMapper;
    private final AttrValueMapper attrValueMapper;

    public AttrKeyServiceImpl(AttrKeyMapper attrKeyMapper, AttrValueMapper attrValueMapper) {
        this.attrKeyMapper = attrKeyMapper;
        this.attrValueMapper = attrValueMapper;
    }

    @Override
    public List<AttrKeyVo> listAttrInfo() {
        return attrKeyMapper.listAttrInfo();
    }


    /**
     * 根据属性名称Id删除属性名称以及对应属性值
     * 注：使用mapper.delete()进行删除时，不会进行字段填充
     * 因为没有传入实体，mybatis-plus的字段填充依赖实体信息
     * @param attrKeyId 属性名称Id
     */
    @Override
    @Transactional
    public void removeAttrKeyById(Long attrKeyId) {
        attrKeyMapper.deleteById(attrKeyId);
        LambdaQueryWrapper<AttrValue> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(AttrValue::getAttrKeyId, attrKeyId);
        List<AttrValue> attrValues = attrValueMapper.selectList(lambdaQueryWrapper);
        List<Long> idList = attrValues.stream()
                .map(AttrValue::getId)
                .toList();
        attrValueMapper.deleteByIds(idList);
    }
}




