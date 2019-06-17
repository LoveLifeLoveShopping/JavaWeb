package cn.com.demo.system.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 
 * 登录拦截器
 * @author min
 *
 */
public class LoingInterceptor implements HandlerInterceptor{


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        Object username = request.getSession().getAttribute("username");
        if (null == username || !(username instanceof String)) {           
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
