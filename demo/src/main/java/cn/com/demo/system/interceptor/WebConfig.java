package cn.com.demo.system.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WEB拦截
 * 
 * @author min
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	LoingInterceptor loginInterceptor;

	/**
	 * 忽略静态资源拦截
	 * 
	 */
	final String[] ignoreInterception = { "/static/**", "/bootstrap-3.3.7-dist/**", "/css/**", "/jquery-3.3.1/**",
			"js/**", "/assets/**", "/login", "/error/**","/resources/**" ,"/images/**"};

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(ignoreInterception);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/**
	 * 配置不需要经过controller就跳转到登录页面
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");

	}

	/***
	 * addResourceLocations指的是文件放置的目录，addResoureHandler指的是对外暴露的访问路径
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 排除静态资源拦截
		registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}

}
