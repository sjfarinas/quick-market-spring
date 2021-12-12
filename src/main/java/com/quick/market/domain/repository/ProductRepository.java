package com.quick.market.domain.repository;

import com.quick.market.domain.DomainProduct;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<DomainProduct> getAll();
    Optional<List<DomainProduct>> getByCategory(int categoryId);
    Optional<List<DomainProduct>> getLowProducts(int quantity);
    Optional<DomainProduct> getProduct(int productId);
    DomainProduct save(DomainProduct product);
    void delete(int productId);
}
