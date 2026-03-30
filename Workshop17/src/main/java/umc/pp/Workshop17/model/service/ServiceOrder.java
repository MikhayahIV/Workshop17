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

    public static class Builder {
        private ServiceOrder so = new ServiceOrder();

        public Builder initiate(Customer customer, Vehicle vehicle, String complaint) {
            so.customer = customer;
            so.vehicle = vehicle;
            so.customerComplaint = complaint;
            so.entryDate = LocalDateTime.now();
            so.status = ServiceOrderStatus.OPEN;
            return this;
        }

        public Builder withChecklist(EntryCheckList checklist) {
            so.entryChecklist = checklist;
            return this;
        }

        public Builder assignMechanic(Mechanic mechanic) {
            so.mechanic = mechanic;
            return this;
        }

        public Builder setEstimates(LocalDateTime estimatedDate, String protocol) {
            so.estimatedDeliveryDate = estimatedDate;
            so.protocolNumber = protocol;
            return this;
        }

        public ServiceOrder build() {
            if (so.customer == null || so.vehicle == null || so.protocolNumber == null) {
                throw new IllegalStateException("Mandatory OS fields missing.");
            }
            return so;
        }
    }


}
