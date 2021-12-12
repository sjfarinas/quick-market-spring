package com.quick.market.domain.repository;

import com.quick.market.domain.DomainPurchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    List<DomainPurchase> getAll();
    Optional<List<DomainPurchase>> getByPartnerId(String partnerId);
    DomainPurchase save(DomainPurchase purchase);
}
