package com.venturedive.filters;

import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

public class RequestBlockingFilter implements Filter {

    private ServletContext context;


    public void init(FilterConfig filterConfig) throws ServletException {

        this.context = filterConfig.getServletContext();
        this.context.log("RequestBlockingFilter initialized");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        UserAgent userAgent = UserAgent.parseUserAgentString(req.getHeader("User-agent"));

        if (userAgent.getBrowser().getName().equals("Firefox")) {
            PrintWriter out = servletResponse.getWriter();
            out.println("<font color=red>You are not authorized to enter</font>");
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }


    }

    public void destroy() {
        //we can close resources here
    }
}
