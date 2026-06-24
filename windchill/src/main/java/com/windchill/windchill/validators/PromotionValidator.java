package com.windchill.windchill.validators;

import com.windchill.windchill.model.WTPart;

public class PromotionValidator {

    private LifecycleValidator
            lifecycleValidator;
    
    private ComplianceDocumentPromotionValidator complianceDocumentPromotionValidator;

    public PromotionValidator(
            LifecycleValidator lifecycleValidator,
            ComplianceDocumentPromotionValidator complianceDocumentPromotionValidator) {

        this.lifecycleValidator =
                lifecycleValidator;
        this.complianceDocumentPromotionValidator = complianceDocumentPromotionValidator;
    }

    public void validate(WTPart part) {

        System.out.println("PromotionValidator: starting lifecycle validation for part: " + part);
        lifecycleValidator.validate(part);
        System.out.println("PromotionValidator: completed lifecycle validation for part: " + part);

        System.out.println("PromotionValidator: starting compliance document validation for part: " + part);
        complianceDocumentPromotionValidator.validateComplianceDocuments(part);
        System.out.println("PromotionValidator: completed compliance document validation for part: " + part);
    }
}