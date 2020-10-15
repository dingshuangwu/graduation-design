package dingshuangwu.graduation.graduationo2o.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 支持跨域访问
 *
 * @author dingshuangwu
 * @date 2019-12-27
 */
@Configuration
public class CorsConfig {
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 1允许任何域名使用
        corsConfiguration.addAllowedOrigin("*");
        // 前端开启withCredentials时不能为*,不然会跨域,必须指定具体的域.
//        corsConfiguration.addAllowedOrigin("http://127.0.0.1");
//        corsConfiguration.setAllowCredentials(true);
        // 2允许任何头
        corsConfiguration.addAllowedHeader("*");
        // 3允许任何方法（post、get等）
        corsConfiguration.addAllowedMethod("*");
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 注册过滤器
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}
