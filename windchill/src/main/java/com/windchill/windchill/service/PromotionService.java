package com.windchill.windchill.service;

import java.util.ArrayList;

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

    public PromotionNotice promote(
            PromotionNotice notice) {

            PromotionNotice promotionNotice = new PromotionNotice(new ArrayList<>());
        for(WTPart part :
                notice.getPromotables()) {

            System.out.println(
                    "Promoting part: in promotion service" + part   );
            try {

                validator.validate(part);
            }
            catch(Exception e)  {
                throw new RuntimeException("Not able to process the part " +part.toString());
            }

        }

        for(WTPart part : notice.getPromotables()) {
             part.setLifecycleState(
                    "RELEASED");

            System.out.println("Promoting part: " + part.getNumber() + " to state: " + part.getLifecycleState());

            repository.save(part);
            promotionNotice.getPromotables().add(part);
          }
        return promotionNotice;
    }
}