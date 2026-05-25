package umc.pp.Workshop17.service.services;

import umc.pp.Workshop17.model.service.ServiceOrder;

import java.math.BigDecimal;

public class BasePricingCalculator implements PricingCalculator{
    @Override
    public BigDecimal calculate(ServiceOrder serviceOrder) {
        return serviceOrder.getPartsValue().add(serviceOrder.getLaborValue());
    }
}
