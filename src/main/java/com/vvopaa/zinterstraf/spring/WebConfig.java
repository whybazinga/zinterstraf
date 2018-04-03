package com.vvopaa.zinterstraf.spring;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

 	@Bean(name = "appMessages")
    public MessageSource messageSource() {
	    ResourceBundleMessageSource source = new ResourceBundleMessageSource();
	    source.setBasename("messages");
	    source.setDefaultEncoding("UTF-8");
	    return source;
    }

    /**
	 * Hint: addResourceHandler changes pattern to any wanted dir name
	 * @param registry - web options to be configured
     */
 	@Override
 	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/global/**")) {
            registry.addResourceHandler("/global/**")
                .addResourceLocations("classpath:/global/")
                .setCachePeriod(4600);
        }
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
