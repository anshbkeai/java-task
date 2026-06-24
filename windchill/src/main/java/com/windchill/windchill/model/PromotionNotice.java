package com.windchill.windchill.model;

import java.util.List;

public class PromotionNotice {

    private List<WTPart> promotables;

    public PromotionNotice(List<WTPart> promotables) {
        this.promotables = promotables;
    }
    
    public PromotionNotice() {
    }

    public List<WTPart> getPromotables() {
        return promotables;
    }

    @Override
    public String toString() {
        return "PromotionNotice [promotables=" + promotables.stream().map(x -> x.toString()).reduce((a, b) -> a + ", " + b).orElse("") + "]";
    }

}
