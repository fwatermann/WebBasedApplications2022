package de.fhbi.webbasedapps.projektsammlung.rest;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CorsFilter", urlPatterns = "/*")
public class CorsFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {}

    public void destroy() { }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        if(response instanceof HttpServletResponse) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,PATCH,DELETE,OPTIONS");
            httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        }

        chain.doFilter(request, response);
    }
}
