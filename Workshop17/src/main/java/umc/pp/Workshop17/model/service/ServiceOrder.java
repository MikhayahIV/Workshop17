package umc.pp.Workshop17.model.service;

import jakarta.persistence.*;
import umc.pp.Workshop17.model.customer.Customer;
import umc.pp.Workshop17.model.staff.Mechanic;
import umc.pp.Workshop17.model.vehicle.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_service_order")
public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String protocolNumber;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String customerComplaint; //reclamacao do clientes

    @Column(columnDefinition = "TEXT")
    private String mechanicDiagnostic; // diagnostico do mecanico

    @Enumerated(EnumType.STRING)
    private ServiceOrderStatus status = ServiceOrderStatus.OPEN;

    private BigDecimal partsValue = BigDecimal.ZERO;
    private BigDecimal laborValue = BigDecimal.ZERO;
    private BigDecimal totalValue = BigDecimal.ZERO;

    private LocalDateTime entryDate;
    private LocalDateTime estimatedDeliveryDate;
    private LocalDateTime finishDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "mechanic_id") // Pode começar sem mecânico definido
    private Mechanic mechanic;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "checklist_id", unique = true)
    private EntryCheckList entryChecklist;

    protected ServiceOrder(){

    }

    private ServiceOrder(Builder builder){
        this.protocolNumber = builder.protocolNumber;
        this.customerComplaint = builder.customerComplaint;
        this.entryDate = builder.entryDate;
        this.estimatedDeliveryDate = builder.estimatedDeliveryDate;
        this.customer = builder.customer;
        this. vehicle = builder.vehicle;
        this.mechanic = builder.mechanic;
        this.entryChecklist = builder.entryChecklist;
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

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public EntryCheckList getEntryChecklist() {
        return entryChecklist;
    }

    public ServiceOrderStatus getStatus() {
        return status;
    }

    public void updateExecutionDetails(String diagnostic, BigDecimal parts, BigDecimal labor) {
        this.mechanicDiagnostic = diagnostic;
        this.partsValue = parts != null ? parts : BigDecimal.ZERO;
        this.laborValue = labor != null ? labor : BigDecimal.ZERO;
        this.totalValue = this.partsValue.add(this.laborValue);
    }

    public void finishOS() {
        this.status = ServiceOrderStatus.COMPLETED;
        this.finishDate = LocalDateTime.now();
    }

    public void cancelOS(){
        this.status = ServiceOrderStatus.CANCELLED;
        this.finishDate = LocalDateTime.now();
    }

    public void updateEstimatedDate(LocalDateTime newDate) {
        this.estimatedDeliveryDate = newDate;
    }

    public static class Builder {

        private String protocolNumber;
        private String customerComplaint;
        private LocalDateTime entryDate;
        private LocalDateTime estimatedDeliveryDate;
        private Customer customer;
        private Vehicle vehicle;
        private Mechanic mechanic;
        private EntryCheckList entryChecklist;

        public Builder initiate(Customer customer, Vehicle vehicle, String complaint, String protocolNumber) {
            this.customer = customer;
            this.vehicle = vehicle;
            this.customerComplaint = complaint;
            this.protocolNumber = protocolNumber;
            this.entryDate = LocalDateTime.now();
            return this;
        }

        public Builder withChecklist(EntryCheckList checklist) {
            this.entryChecklist = checklist;
            return this;
        }

        public Builder assignMechanic(Mechanic mechanic) {
            this.mechanic = mechanic;
            return this;
        }

        public Builder setEstimates(LocalDateTime estimatedDate) {
            this.estimatedDeliveryDate = estimatedDate;
            return this;
        }

        public ServiceOrder build() {
            if (this.customer == null || this.vehicle == null || this.protocolNumber == null) {
                throw new IllegalStateException("Mandatory OS fields missing.");
            }
            return new ServiceOrder(this);
        }
    }

}
