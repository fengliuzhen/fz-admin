package com.fz.admin.auth;

import com.fz.admin.Service.RedisService;
import com.fz.admin.core.ConvertCore;
import com.fz.admin.core.CookieCore;
import com.fz.admin.core.SystemEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Interceptor implements HandlerInterceptor {
    //监控加载 时间
    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("StopWatch-StartTime");

    //日志
    Logger logger = LoggerFactory.getLogger(Interceptor.class);

    @Autowired
    private RedisService redisService;

    /**
     * controller 执行之前调用
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断Session是否为空
        String tmpSessionId= CookieCore.initLoginCookie(request,response);
        String objSession=redisService.get(SystemEnum.Admin_Sid.getValue()+tmpSessionId);
        int adminId= ConvertCore.ObjToInt(objSession);
        if(adminId<=0){
            response.sendRedirect("/login");
            return false;
        }
        else
        {
            //判断权限
        }
        long beginTime = System.currentTimeMillis();//1、开始时间
        startTimeThreadLocal.set(beginTime);//线程绑定变量（该数据只有当前请求的线程可见）
        return true;
    }

    /**
     * controller 执行之后，且页面渲染之前调用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //System.out.println("------postHandle-----");
    }

    /**
     * 页面渲染之后调用，一般用于资源清理操作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long endTime = System.currentTimeMillis();//2、结束时间
        long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
        long consumeTime = endTime - beginTime;//3、消耗的时间

        if (consumeTime > 500) {//此处认为处理时间超过500毫秒的请求为慢请求
            //记录日志
            logger.info(String.format("页面加载慢时间为：%s 毫秒,URL:%s", consumeTime, request.getRequestURI()));
        }
    }
}
