package com.windchill.windchill.repository;

import java.util.HashMap;
import java.util.Map;

import com.windchill.windchill.model.WTPart;

public class PartRepository {

    private Map<Long, WTPart> database
            = new HashMap<>();

    public WTPart findById(Long id) {

        return database.get(id);
    }

    public void save(WTPart part) {

        database.put(
                part.getId(),
                part
        );
    }
}