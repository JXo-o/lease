package com.jxh.lease.web.admin.custom.config;

import com.jxh.lease.web.admin.custom.converter.StringToBaseEnumConverterFactory;
import lombok.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: WebMvcConfiguration
 * Package: com.jxh.lease.web.admin.custom.config
 * Description:
 *
 * @author JX
 * @version 1.0
 * @date 2024/7/5 15:41
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final StringToBaseEnumConverterFactory stringToBaseEnumConverterFactory;

    public WebMvcConfiguration(StringToBaseEnumConverterFactory stringToBaseEnumConverterFactory) {
        this.stringToBaseEnumConverterFactory = stringToBaseEnumConverterFactory;
    }

    @Override
    public void addFormatters(@NonNull FormatterRegistry registry) {
        registry.addConverterFactory(stringToBaseEnumConverterFactory);
    }
}
