package com.powernode.config;

import cn.hutool.core.util.ObjectUtil;
import com.powernode.constant.AuthConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * feign拦截器
 * 作用：解决服务之间调用没有token的情况
 *
 * 浏览器-》A服务-》B服务
 *
 * 定时器-》A服务,给一个固定token
 */
public class FeighInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //获取当前请求的上下文对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //判断是否有值
        if (ObjectUtil.isNotNull(requestTemplate)) {
            //获取请求对象
            HttpServletRequest request = requestAttributes.getRequest();
            //判断request对象是否为空
            if (ObjectUtil.isNotNull(request)) {
                //获取当前请求头中的token值，传递到需要一个请求对象的请求头中
                String authorization = request.getHeader(AuthConstants.AUTHORIZATION);
                requestTemplate.header(AuthConstants.AUTHORIZATION, authorization);
                return;
            }
        }
        requestTemplate.header(AuthConstants.AUTHORIZATION,AuthConstants.BERAER+"e3627269-8327-4165-875f-856adc44592f");
    }
}
