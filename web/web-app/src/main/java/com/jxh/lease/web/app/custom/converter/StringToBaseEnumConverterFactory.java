package com.jxh.lease.web.app.custom.converter;

import com.jxh.lease.model.enums.BaseEnum;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * ClassName: StringToBaseEnumConverterFactory
 * Package: com.jxh.lease.web.app.custom.converter
 * Description:
 *
 * @author JX
 * @version 1.0
 * @date 2024/7/10 23:54
 */
@Component
public class StringToBaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {
    @NonNull
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(@NonNull Class<T> targetType) {
        return source -> {
            for (T enumConstant : targetType.getEnumConstants()) {
                if (enumConstant.getCode().equals(Integer.valueOf(source))) {
                    return enumConstant;
                }
            }
            throw new IllegalArgumentException("非法的枚举值:" + source);
        };
    }
}
