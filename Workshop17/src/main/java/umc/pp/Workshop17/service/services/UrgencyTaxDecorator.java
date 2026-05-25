package umc.pp.Workshop17.service.services;

import umc.pp.Workshop17.model.service.ServiceOrder;

import java.math.BigDecimal;

public class UrgencyTaxDecorator extends PricingDecorator{

    public UrgencyTaxDecorator(PricingCalculator wrappedCalculator) {
        super(wrappedCalculator);
    }

    @Override
    public BigDecimal calculate(ServiceOrder serviceOrder) {
        BigDecimal currentTotal = super.calculate(serviceOrder);

        boolean isUrgent = serviceOrder.getEstimatedDeliveryDate() != null &&
                serviceOrder.getEstimatedDeliveryDate().toLocalDate().isEqual(serviceOrder.getEntryDate().toLocalDate());

        if(isUrgent){
            return currentTotal.multiply(new BigDecimal("1.10"));
        }
        return currentTotal;
    }
}
