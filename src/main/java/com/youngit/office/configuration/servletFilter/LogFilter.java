package com.youngit.office.configuration.servletFilter;

import jakarta.servlet.*;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        System.out.println("LogFilter init 2: LogFilter class init()");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)  throws IOException, ServletException {
        System.out.println("api 1: LogFilter class doFilter()");
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String requestURI = httpRequest.getRequestURI();
        System.out.println("doFilter(), requestURI: " + requestURI);
        System.out.println("doFilter(), httpResponse: " + httpResponse);
        try {
            servletRequest.setCharacterEncoding("UTF-8");
            filterChain.doFilter(servletRequest, servletResponse);

        } catch (Exception e) {
            e.getStackTrace();
            // log.error("LoginCheckFilter error", e);
        }
    }

    @Override
    public void destroy()
    {
        System.out.println("LogFilter class destroy()");
        Filter.super.destroy();
    }
}
