package com.windchill.windchill.model;

public class WTDocument {

    private Long id;
    private String documentType;

    public WTDocument(Long id,
                      String documentType) {

        this.id = id;
        this.documentType = documentType;
    }

    public Long getId() {
        return id;
    }

    public WTDocument() {
    }

    public String getDocumentType() {
        return documentType;
    }

    @Override
    public String toString() {
        return "WTDocument [id=" + id + ", documentType=" + documentType + "]";
    }
}
