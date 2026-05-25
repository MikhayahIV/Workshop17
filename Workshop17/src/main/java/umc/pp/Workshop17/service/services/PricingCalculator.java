package umc.pp.Workshop17.service.services;

import umc.pp.Workshop17.model.service.ServiceOrder;

import java.math.BigDecimal;

public interface PricingCalculator {
    BigDecimal calculate(ServiceOrder serviceOrder);
}
