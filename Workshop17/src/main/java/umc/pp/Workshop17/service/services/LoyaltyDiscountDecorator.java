package umc.pp.Workshop17.service.services;

import umc.pp.Workshop17.model.service.ServiceOrder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LoyaltyDiscountDecorator extends PricingDecorator{
    public LoyaltyDiscountDecorator(PricingCalculator wrappedCalculator) {
        super(wrappedCalculator);
    }

    @Override
    public BigDecimal calculate(ServiceOrder serviceOrder) {
        BigDecimal currentTotal = super.calculate(serviceOrder);

        LocalDateTime registrationDate = serviceOrder.getCustomer().getRegistrationDate();

        if(registrationDate != null && registrationDate.isBefore(LocalDateTime.now().minusYears(1))){
            return currentTotal.multiply(new BigDecimal("0.95"));
        }
        return currentTotal;
    }
}
