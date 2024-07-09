package com.jxh.lease.web.admin.custom.config;

import com.jxh.lease.web.admin.custom.converter.StringToBaseEnumConverterFactory;
import com.jxh.lease.web.admin.custom.interceptor.AuthenticationInterceptor;
import lombok.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
    private final AuthenticationInterceptor authenticationInterceptor;

    public WebMvcConfiguration(
            StringToBaseEnumConverterFactory stringToBaseEnumConverterFactory,
            AuthenticationInterceptor authenticationInterceptor
    ) {
        this.stringToBaseEnumConverterFactory = stringToBaseEnumConverterFactory;
        this.authenticationInterceptor = authenticationInterceptor;
    }

    @Override
    public void addFormatters(@NonNull FormatterRegistry registry) {
        registry.addConverterFactory(stringToBaseEnumConverterFactory);
    }

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login/**");
    }

}
