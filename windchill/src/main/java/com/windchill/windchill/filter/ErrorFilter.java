package com.windchill.windchill.filter;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/*")
public class ErrorFilter implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            response.setContentType("application/json");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(new ErrorResponse(e.getMessage()));
            response.getWriter().write(json);
        }
    }

}
