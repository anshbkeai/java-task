package com.windchill.windchill.validators;

import com.windchill.windchill.model.WTPart;

public class LifecycleValidator {

    public void validate(WTPart part) {

        if("OBSOLETE".equals(
                part.getLifecycleState())) {

            throw new RuntimeException(
                    "Obsolete parts cannot be promoted");
        }
    }
}