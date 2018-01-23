package com.vvopaa.zinterstraf.spring;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScans(value = {@ComponentScan("com.vvopaa.zinterstraf")})
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
	}
	
 	@Bean
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
 	        registry.addResourceHandler("/resources/**")
 	        .addResourceLocations("/resources/")
 	        .setCachePeriod(4600);
    }
 
}
