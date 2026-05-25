package umc.pp.Workshop17.service.services;

import umc.pp.Workshop17.model.service.ServiceOrder;

import java.math.BigDecimal;

public abstract class PricingDecorator implements PricingCalculator{
    protected final PricingCalculator wrappedCalculator;

    public PricingDecorator(PricingCalculator wrappedCalculator) {
        this.wrappedCalculator = wrappedCalculator;
    }

    @Override
    public BigDecimal calculate(ServiceOrder serviceOrder) {
        return wrappedCalculator.calculate(serviceOrder);
    }
}
