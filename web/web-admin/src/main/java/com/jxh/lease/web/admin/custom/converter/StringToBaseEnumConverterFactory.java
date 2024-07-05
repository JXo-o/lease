package com.jxh.lease.web.admin.custom.converter;

import com.jxh.lease.model.enums.BaseEnum;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * ClassName: StringToBaseEnumConverterFactory
 * Package: com.jxh.lease.web.admin.custom.converter
 * Description:
 *
 * @author JX
 * @version 1.0
 * @date 2024/7/5 15:26
 */
@Component
public class StringToBaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {
    @NonNull
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(@NonNull Class<T> targetType) {
        return new Converter<String, T>() {
            @Override
            public T convert(@NonNull String source) {
                for (T enumConstant : targetType.getEnumConstants()) {
                    if (enumConstant.getCode().equals(Integer.valueOf(source))) {
                        return enumConstant;
                    }
                }
                throw new IllegalArgumentException("非法的枚举值:" + source);
            }
        };
    }
}
