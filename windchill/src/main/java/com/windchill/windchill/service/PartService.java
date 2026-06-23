package com.windchill.windchill.service;

import com.windchill.windchill.model.WTPart;
import com.windchill.windchill.repository.PartRepository;

public class PartService {

    private PartRepository repository;

    public PartService(
            PartRepository repository) {

        this.repository = repository;
    }

    public WTPart find(Long id) {

        return repository.findById(id);
    }
}