package com.himedia.usrserv.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {

	@Bean
    public FilterRegistrationBean corsFilterRegistrationBean() {
        // 对响应头进行CORS授权
		CorsRegistration corsRegistration = new CorsRegistration("/**");
        this.configCorsParams(corsRegistration);

        // 注册CORS过滤器
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        CorsFilter corsFilter = new CorsFilter(configurationSource);
        return new FilterRegistrationBean(corsFilter);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 配置CorsInterceptor的CORS参数
        this.configCorsParams(registry.addMapping("/**"));
    }
    
    private void configCorsParams(CorsRegistration corsRegistration) {
    	CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
    	
        corsRegistration.allowedOrigins(configuration.getAllowedOrigins().toArray(new String[0]))
                .allowedMethods(configuration.getAllowedMethods().toArray(new String[0]))
                .allowedHeaders(configuration.getAllowedHeaders().toArray(new String[0]))
                .exposedHeaders(configuration.getExposedHeaders().toArray(new String[0]))
                .allowCredentials(configuration.getAllowCredentials())
                .maxAge(configuration.getMaxAge());
    }
}
