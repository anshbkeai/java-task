package com.windchill.windchill.service;

import com.windchill.windchill.model.PromotionNotice;
import com.windchill.windchill.model.WTPart;
import com.windchill.windchill.repository.PartRepository;
import com.windchill.windchill.validators.PromotionValidator;

public class PromotionService {

    private PromotionValidator validator;

    private PartRepository repository;

    public PromotionService(
            PromotionValidator validator,
            PartRepository repository) {

        this.validator = validator;
        this.repository = repository;
    }

    public void promote(
            PromotionNotice notice) {

        for(WTPart part :
                notice.getPromotables()) {

            validator.validate(part);

            part.setLifecycleState(
                    "RELEASED");

            repository.save(part);
        }
    }
}
