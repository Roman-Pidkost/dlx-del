package ua.com.deluxedostavka.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class StaticResourceConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("file:/home/admin/web/aa/public_html/resources/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/","classpath:/resources/","classpath:/static/","classpath:/public/");
    }
}