package com.jxh.lease.common.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * ClassName: MybatisMetaObjectHandler
 * Package: com.jxh.lease.common.mybatisplus
 * Description:
 *
 * @author JX
 * @version 1.0
 * @date 2024/7/5 10:39
 */
@Component
public class MybatisMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //this.setFieldValByName("updateTime", new Date(), metaObject);
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }
}
