package io.exhub.exhub_manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author Administrator
 */
@Configuration
@EnableWebMvc
@ComponentScan("io")
public class MvcConfiguration implements WebMvcConfigurer {


	@Override
	public void addCorsMappings(CorsRegistry registry) {


		registry.addMapping("/**")
                .allowedOrigins("*")
                .maxAge(3600)
				.allowCredentials(true);

				//.allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE")
				//.maxAge(3600);
	}

	@Bean
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/templates/");
		resolver.setSuffix(".html");
		return resolver;
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		//addResourceHandler:指的是对外暴露的文件路径
		//addResourceLocations：文件放置的目录
		registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
		//将所有/static/** 访问都映射到classpath:/static/ 目录下
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {

		registry.viewResolver(getViewResolver());
	}
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseRegisteredSuffixPatternMatch(false);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		//registry.addViewController("/error").setViewName("");
		//registry.addViewController("/index").setViewName("index");
		//registry.addViewController("/login").setViewName("login");

	}


}
