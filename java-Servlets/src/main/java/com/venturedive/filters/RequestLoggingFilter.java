package com.venturedive.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;

public class RequestLoggingFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig filterConfig) throws ServletException {

        this.context = filterConfig.getServletContext();
        this.context.log("RequestLoggingFilter initialized");

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.context.log("Timestamp :" + timestamp + " - IP Address" + req.getRemoteAddr());

        // pass the request along the filter chain
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

        //we can close resources here

    }
}
