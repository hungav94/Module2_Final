package com.codegym.service;

import com.codegym.model.ProductType;

public interface ProductTypeService {
    Iterable<ProductType> findAll();
}
