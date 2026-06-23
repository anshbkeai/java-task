package com.windchill.windchill.model;

import java.util.List;

public class PromotionNotice {

    private List<WTPart> promotables;

    public PromotionNotice(List<WTPart> promotables) {
        this.promotables = promotables;
    }

    public List<WTPart> getPromotables() {
        return promotables;
    }
}
