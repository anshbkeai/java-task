package com.windchill.windchill.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.windchill.windchill.model.WTDocument;

public class DocumentService {

    private Map<Long, List<WTDocument>> documents
            = new HashMap<>();

    public List<WTDocument> getDocuments(Long partId) {

        return documents.getOrDefault(
                partId,
                Collections.emptyList()
        );
    }

    public void attachDocument(
            Long partId,
            WTDocument document) {

        documents
                .computeIfAbsent(
                        partId,
                        k -> new ArrayList<>())
                .add(document);
    }
}