package com.quick.market.persistence;

import com.quick.market.domain.DomainPurchase;
import com.quick.market.persistence.crud.PurchaseCrudRepository;
import com.quick.market.persistence.entity.Purchase;
import com.quick.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PurchaseRepository implements com.quick.market.domain.repository.PurchaseRepository {

    @Autowired
    private PurchaseCrudRepository purchaseCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<DomainPurchase> getAll() {
        return mapper.toPurchases((List<Purchase>) purchaseCrudRepository.findAll());
    }

    @Override
    public Optional<List<DomainPurchase>> getByPartnerId(String partnerId) {
        return purchaseCrudRepository.findByPartnerId(partnerId)
                .map(purchases -> mapper.toPurchases(purchases));
    }

    @Override
    public DomainPurchase save(DomainPurchase domainPurchase) {
        Purchase purchase = mapper.toPurchase(domainPurchase);
        purchase.getProducts().forEach(product -> product.getPurchase());
        return mapper.toPurchase(purchaseCrudRepository.save(purchase));
    }
}
