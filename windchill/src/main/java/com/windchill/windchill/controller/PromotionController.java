package com.windchill.windchill.controller;


import java.io.IOException;

import com.windchill.windchill.model.PromotionNotice;
import com.windchill.windchill.service.PromotionService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/promote")
public class PromotionController extends HttpServlet {

    


    public void promote(
            PromotionNotice notice) {
        
        PromotionService service =
                new PromotionService(
                        new com.windchill.windchill.validators.PromotionValidator(
                                new com.windchill.windchill.validators.LifecycleValidator()),
                        new com.windchill.windchill.repository.PartRepository()
                );
        service.promote(notice);
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
        // api that goonal work 
        System.out.println("Promotion API is working");
        resp.getWriter().write("Promotion API is working");
    };

    
}
