package com.windchill.windchill.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.windchill.windchill.model.PromotionNotice;
import com.windchill.windchill.model.WTDocument;
import com.windchill.windchill.model.WTPart;
import com.windchill.windchill.repository.PartRepository;
import com.windchill.windchill.validators.PromotionValidator;

public class PromotionServiceTest {

    private PromotionService promotionService;

    private PromotionValidator promotionValidator;
    private PartRepository partRepository;

    private WTPart part1;
    private WTPart part2;
    private WTPart part3;
    private WTPart part4;

    private WTDocument doc1;
    private WTDocument doc2;
    private WTDocument doc3;
    private WTDocument doc4;

    //private WTDocument document2;
    private PromotionNotice notice;
    private PromotionNotice bulkNotice;

    @BeforeEach
    public void setup() {
         promotionValidator = mock(PromotionValidator.class);
         partRepository = mock(PartRepository.class);

        promotionService = new PromotionService(promotionValidator, partRepository);

        part1 = new WTPart(
        101L,
        "PART-001",
        "MECHANICAL",
        "INWORK");

         part2 = new WTPart(
                102L,
                "PART-001",
                "MECHANICAL",
                "RELEASED");

         part3 = new WTPart(
                103L,
                "PART-001",
                "ELECTRICAL",
                "RELEASED");

         part4 = new WTPart(
                104L,
                "PART-001",
                "MECHANICAL",
                "INWORK");

        doc1 = new WTDocument(101L, "DOC-001");
        doc2 = new WTDocument(102L, "DOC-002");
        doc3 = new WTDocument(103L, "DOC-003");
        doc4 = new WTDocument(104L, "Compliance".toUpperCase());

        notice = new PromotionNotice(List.of(part1));

        bulkNotice = new PromotionNotice(
            List.of(
                    part1,
                    part2,
                    part3,
                    part4
            )
        );
    }
    
    @Test
    public void partMechnicalComplianceNotAttach() {
        PromotionNotice promotionNotice = promotionService.promote(notice);

        assertNotNull(promotionNotice);
        System.out.println(promotionNotice.toString());

        verify(promotionValidator).validate(part1);
        verify(partRepository).save(part1);


    }

}
