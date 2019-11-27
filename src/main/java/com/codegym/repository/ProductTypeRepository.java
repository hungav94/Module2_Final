package com.codegym.repository;

import com.codegym.model.ProductType;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductTypeRepository extends PagingAndSortingRepository<ProductType, Long> {
}
