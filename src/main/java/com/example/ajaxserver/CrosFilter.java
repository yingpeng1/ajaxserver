package com.example.ajaxserver;

import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CrosFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) servletResponse;

        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String origin = req.getHeader("Origin");

        if (!StringUtils.isEmpty(origin)){
            //带cookie的时候，origin必须是全匹配，不能使用*
            res.addHeader("Access-Control-Allow-Origin",origin);
        }

        res.addHeader("Access-Control-Allow-Methods","*");

        String headers = req.getHeader("Access-Control-Allow-Headers");

        if(!StringUtils.isEmpty(headers)){
            //支持所有自定义头
            res.addHeader("Access-Control-Allow-Headers",headers);
        }
        res.addHeader("Access-Control-Max-Age","3600");

        //enable cookie
        res.addHeader("Access-Control-Allow-Credentials","true");

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
