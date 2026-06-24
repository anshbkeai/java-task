package com.windchill.windchill.model;

public class WTPart {

    private Long id;
    private String number;
    private String type;
    private String lifecycleState;

    public WTPart(Long id,
                  String number,
                  String type,
                  String lifecycleState) {

        this.id = id;
        this.number = number;
        this.type = type;
        this.lifecycleState = lifecycleState;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "WTPart [id=" + id + ", number=" + number + ", type=" + type + ", lifecycleState=" + lifecycleState
                + "]";
    }

    public String getType() {
        return type;
    }

    public String getLifecycleState() {
        return lifecycleState;
    }

    public void setLifecycleState(String lifecycleState) {
        this.lifecycleState = lifecycleState;
    }
}