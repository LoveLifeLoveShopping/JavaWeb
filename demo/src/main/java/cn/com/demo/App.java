package cn.com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.alibaba.druid.support.http.StatViewServlet;

/**
 * 启动类
 *
 */

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	/**
	 * 阿里SQL监控用户名和密码 地址： http://127.0.0.1:8080/druid/sql.html
	 * http://127.0.0.1:8080/druid/index.html 首页
	 * @return
	 */
	@Bean
	public ServletRegistrationBean<StatViewServlet> druidStatViewServlet() {
		ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(new StatViewServlet(),
				"/druid/*");
		registrationBean.addInitParameter("allow", "127.0.0.1");// IP白名单 (没有配置或者为空，则允许所有访问)
		registrationBean.addInitParameter("deny", "");// IP黑名单 (存在共同时，deny优先于allow)
		registrationBean.addInitParameter("loginUsername", "root");
		registrationBean.addInitParameter("loginPassword", "root");
		registrationBean.addInitParameter("resetEnable", "false");
		return registrationBean;

	}

}
