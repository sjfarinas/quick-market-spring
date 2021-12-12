package com.quick.market.persistence;

import com.quick.market.domain.DomainProduct;
import com.quick.market.persistence.crud.ProductCrudRepository;
import com.quick.market.persistence.entity.Product;
import com.quick.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements com.quick.market.domain.repository.ProductRepository {
    @Autowired
    private ProductCrudRepository productCrudRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<DomainProduct> getAll() {
        List<Product> products = (List<Product>) productCrudRepository.findAll();
        return mapper.toDomainProducts(products);
    }

    @Override
    public Optional<List<DomainProduct>> getByCategory(int categoryId) {
        List<Product> products = productCrudRepository.findByCategoryIdOrderByNameAsc(categoryId);
        return Optional.of(mapper.toDomainProducts(products));
    }

    @Override
    public Optional<List<DomainProduct>> getLowProducts(int quantity) {
        Optional<List<Product>> products = productCrudRepository.findByStockQuantityLessThanAndStatus(quantity, true);
        return products.map(prods -> mapper.toDomainProducts(prods));
    }

    public Optional<DomainProduct> getProduct(int productId){
        return productCrudRepository.findById(productId).map(product -> mapper.toDomainProduct(product));
    }

    @Override
    public DomainProduct save(DomainProduct domainProduct) {
        Product product = mapper.toProduct(domainProduct);
        return mapper.toDomainProduct(productCrudRepository.save(product));
    }

    @Override
    public void delete(int productId){
        productCrudRepository.deleteById(productId);
    }
}
