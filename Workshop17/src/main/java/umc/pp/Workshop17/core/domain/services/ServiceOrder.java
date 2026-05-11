package umc.pp.Workshop17.core.domain.services;

import umc.pp.Workshop17.core.domain.enums.ServiceOrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ServiceOrder {

    private Long id;

    private String protocolNumber;

    private String customerComplaint;

    private String mechanicDiagnostic;

    private ServiceOrderStatus status = ServiceOrderStatus.OPEN;

    private BigDecimal partsValue = BigDecimal.ZERO;
    private BigDecimal laborValue = BigDecimal.ZERO;
    private BigDecimal totalValue = BigDecimal.ZERO;

    private LocalDateTime entryDate;
    private LocalDateTime estimatedDeliveryDate;
    private LocalDateTime finishDate;

    private UUID customerID;
    private UUID vehicleID;
    private UUID mechanicID;
    private Long entryCheckListID;

    protected ServiceOrder() {
    }

    private ServiceOrder(Builder builder){
        this.protocolNumber = builder.protocolNumber;
        this.customerComplaint = builder.customerComplaint;
        this.entryDate = builder.entryDate;
        this.estimatedDeliveryDate = builder.estimatedDeliveryDate;
        this.customerID = builder.customerID;
        this. vehicleID = builder.vehicleID;
        this.mechanicID = builder.mechanicID;
        this.entryCheckListID = builder.entryChecklistID;
    }

    public Long getId() {
        return id;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public String getCustomerComplaint() {
        return customerComplaint;
    }

    public String getMechanicDiagnostic() {
        return mechanicDiagnostic;
    }

    public ServiceOrderStatus getStatus() {
        return status;
    }

    public BigDecimal getPartsValue() {
        return partsValue;
    }

    public BigDecimal getLaborValue() {
        return laborValue;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public LocalDateTime getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public UUID getCustomerID() {
        return customerID;
    }

    public UUID getVehicleID() {
        return vehicleID;
    }

    public UUID getMechanicID() {
        return mechanicID;
    }

    public Long getEntryCheckListID() {
        return entryCheckListID;
    }

    public void updateExecutionDetails(String diagnostic, BigDecimal parts, BigDecimal labor) {
        if(parts != null && parts.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Parts value cannot be negative");
        }
        this.mechanicDiagnostic = diagnostic;
        this.partsValue = parts != null ? parts : BigDecimal.ZERO;
        this.laborValue = labor != null ? labor : BigDecimal.ZERO;
        calculateTotal();
    }

    private void calculateTotal(){
        this.totalValue = this.partsValue.add(this.laborValue);
    }

    public void finishOS() {
        this.status = ServiceOrderStatus.FINISHED;
        this.finishDate = LocalDateTime.now();
    }

    public void cancelOS(){
        if(this.status == ServiceOrderStatus.FINISHED){
            throw  new IllegalStateException("Completed OS cannot be cancelled");
        }
        this.status = ServiceOrderStatus.CANCELED;
        this.finishDate = LocalDateTime.now();
    }

    public void updateEstimatedDate(LocalDateTime newDate) {
        if(newDate == null || newDate.isBefore(entryDate)){
            throw new IllegalArgumentException("Invalid date");
        }
        this.estimatedDeliveryDate = newDate;
    }

    public static class Builder {

        private String protocolNumber;
        private String customerComplaint;
        private LocalDateTime entryDate;
        private LocalDateTime estimatedDeliveryDate;
        private UUID customerID;
        private UUID vehicleID;
        private UUID mechanicID;
        private Long entryChecklistID;

        public Builder initiate(UUID customer, UUID vehicle, String complaint, String protocolNumber) {
            this.customerID = customer;
            this.vehicleID = vehicle;
            this.customerComplaint = complaint;
            this.protocolNumber = protocolNumber;
            this.entryDate = LocalDateTime.now();
            return this;
        }

        public Builder withChecklist(Long checklist) {
            this.entryChecklistID = checklist;
            return this;
        }

        public Builder assignMechanic(UUID mechanic) {
            this.mechanicID = mechanic;
            return this;
        }

        public Builder setEstimates(LocalDateTime estimatedDate) {
            this.estimatedDeliveryDate = estimatedDate;
            return this;
        }

        public ServiceOrder build() {
            if (this.customerID == null || this.vehicleID == null || this.protocolNumber == null) {
                throw new IllegalStateException("Mandatory OS fields missing.");
            }
            return new ServiceOrder(this);
        }
    }
}
