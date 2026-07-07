package com.windchill.windchill.validators;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.windchill.windchill.model.WTDocument;
import com.windchill.windchill.model.WTPart;
import com.windchill.windchill.service.DocumentService;

public class ComplianceDocumentPromotionValidatorTest {

    private DocumentService documentService;
    private ComplianceDocumentPromotionValidator complianceDocumentPromotionValidator; 

    private WTPart part;

    private WTDocument document;

    private List<WTDocument> documents;
    
    @BeforeEach
    public void setUp() {

        documentService = mock(DocumentService.class);

        complianceDocumentPromotionValidator = new ComplianceDocumentPromotionValidator(documentService);
        part = new WTPart(
                101L,
                "PART-001",
                "MECHANICAL",
                "INWORK");
        document = new WTDocument(101L, "DOC-001");

        documents = new ArrayList<>();

        documents.addAll(List.of(document ,  new WTDocument(101L, "Compliance".toUpperCase())));
    }

    @Test
    void partMechaicalDocumentAttached() {
        when(documentService.getDocuments(101L)).thenReturn(documents);

        complianceDocumentPromotionValidator.validateComplianceDocuments(part);

    } 

    @Test
    void partMechaicalDocumentNotAttached() {
        part = new WTPart(
                101L,
                "PART-001",
                "MECHANICAL",
                "INWORK");
        documents = new ArrayList<>(List.of(new WTDocument(102L, "DOC-102")));

        when(documentService.getDocuments(part.getId())).thenReturn(documents);

        
        assertThrows(RuntimeException.class, () -> {
            complianceDocumentPromotionValidator.validateComplianceDocuments(part);
            System.out.println("Runtime");
        });

    }

    @Test
    void partOtherDocumentAttached() {
        part = new WTPart(
                101L,
                "PART-001",
                "ELECTRICAL",
                "INWORK");

        when(documentService.getDocuments(101L)).thenReturn(documents);

        complianceDocumentPromotionValidator.validateComplianceDocuments(part);
        
    }
}
