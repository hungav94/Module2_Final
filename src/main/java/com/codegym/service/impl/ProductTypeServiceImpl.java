package com.codegym.service.impl;

import com.codegym.model.ProductType;
import com.codegym.repository.ProductTypeRepository;
import com.codegym.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    public ProductTypeRepository productTypeRepository;

    @Override
    public Iterable<ProductType> findAll() {
        return productTypeRepository.findAll();
    }
}
