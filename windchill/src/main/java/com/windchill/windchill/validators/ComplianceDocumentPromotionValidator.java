package com.windchill.windchill.validators;

import java.util.List;

import com.windchill.windchill.model.WTDocument;
import com.windchill.windchill.model.WTPart;
import com.windchill.windchill.service.DocumentService;

public class ComplianceDocumentPromotionValidator {

        // need to implement the logic for validating compliance documents during promotion
        // need the so Promotional Service will redirect me to this class for validation of compliance documents during promotion
        private final DocumentService documentService;

        public ComplianceDocumentPromotionValidator(DocumentService documentService) {
            this.documentService = documentService;
        }
        public void validateComplianceDocuments(WTPart part) {
            // need only for if the part is Mechnical
            if ("MECHANICAL".equals(part.getType())) {
                // check if the part has any compliance documents
                List<WTDocument> complianceDocuments = documentService.getDocuments(part.getId());
                complianceDocuments.stream().filter(doc -> "COMPLIANCE".equals(doc.getDocumentType())).findAny().orElseThrow(() -> new RuntimeException("Release blocked:\n" + //
                                        "Compliance Document must be attached before releasing this part."));


            }
        }
}