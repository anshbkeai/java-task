package com.windchill.windchill.validators;

import com.windchill.windchill.model.WTPart;

public class PromotionValidator {

    private LifecycleValidator
            lifecycleValidator;

    public PromotionValidator(
            LifecycleValidator lifecycleValidator) {

        this.lifecycleValidator =
                lifecycleValidator;
    }

    public void validate(WTPart part) {

        lifecycleValidator.validate(part);
    }
}