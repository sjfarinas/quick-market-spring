package com.quick.market.persistence.mapper;

import com.quick.market.domain.DomainPurchase;
import com.quick.market.persistence.entity.Purchase;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {

    @Mappings({
        @Mapping(source = "purchaseId", target = "purchaseId"),
            @Mapping(source = "partnerId", target = "partnerId"),
            @Mapping(source = "date", target ="date"),
            @Mapping(source = "paymentMethod", target ="paymentMethod"),
            @Mapping(source = "comment", target ="comment"),
            @Mapping(source = "status", target ="status"),
            @Mapping(source = "products", target ="items"),
    })
    DomainPurchase toPurchase(Purchase purchase);
    List<DomainPurchase> toPurchases(List<Purchase> purchases);

    @InheritInverseConfiguration
    @Mapping(target = "partner", ignore = true)
    Purchase toPurchase(DomainPurchase purchase);
}