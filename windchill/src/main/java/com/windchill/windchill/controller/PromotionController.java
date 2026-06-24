package com.windchill.windchill.controller;


import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.windchill.windchill.model.PromotionNotice;
import com.windchill.windchill.model.WTDocument;
import com.windchill.windchill.model.WTPart;
import com.windchill.windchill.repository.PartRepository;
import com.windchill.windchill.service.DocumentService;
import com.windchill.windchill.service.PartService;
import com.windchill.windchill.service.PromotionService;
import com.windchill.windchill.validators.ComplianceDocumentPromotionValidator;
import com.windchill.windchill.validators.LifecycleValidator;
import com.windchill.windchill.validators.PromotionValidator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/promote")
public class PromotionController extends HttpServlet {

        private PromotionService promotionService;
        private PromotionValidator promotionValidator;
        private ComplianceDocumentPromotionValidator complianceDocumentPromotionValidator;
        private PartRepository repository;
        private PartService partService;
        private LifecycleValidator lifecycleValidator;
        private DocumentService documentService;

        @Override
        public void init() throws ServletException {
                this.repository = new PartRepository();
                this.partService = new PartService(this.repository);
                this.lifecycleValidator = new LifecycleValidator();
                this.documentService = new DocumentService();
                this.complianceDocumentPromotionValidator = new ComplianceDocumentPromotionValidator(this.documentService);
                this.promotionValidator = new PromotionValidator(this.lifecycleValidator, this.complianceDocumentPromotionValidator);
                this.promotionService = new PromotionService(promotionValidator, repository); 
        }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException ,IOException {
        // api that goonal work 
        Map<String, String[]> params = req.getParameterMap();
        Long partId = Long.parseLong(params.get("partId")[0]);
        String lifecycleState = params.get("lifecycleState")[0];
        String type = params.get("type")[0];
        String number = params.get("number")[0];

        String documentId = params.getOrDefault("documentType", new String[]{""})[0];


        WTPart part = new WTPart(partId, number, type, lifecycleState);

        System.out.println(part.toString());

        repository.save(part);
        if(!("".equals(documentId))) {
                WTDocument document = new WTDocument(partId, documentId);
                documentService.attachDocument(partId, document);
                resp.setHeader("WTDOC ", document.toString());
        }

        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");


        mapper.writeValue(
                resp.getWriter(),
                part);
    };


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                ObjectMapper mapper =
                        new ObjectMapper();

                System.out.println("PromotionController: doPost called " + req.getRequestURI() ) ;

                PromotionNotice notice =
                        mapper.readValue(
                                req.getInputStream(),
                                PromotionNotice.class);

                System.out.println(notice.toString());

               PromotionNotice promotionNotice = promotionService.promote(notice);

                resp.setContentType("application/json");
                mapper.writeValue(
                        resp.getWriter(),
                        promotionNotice);
    }

    
}