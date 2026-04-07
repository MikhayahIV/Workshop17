package umc.pp.Workshop17.model.customer;

import jakarta.persistence.*;
import umc.pp.Workshop17.model.customer.Address;
import umc.pp.Workshop17.model.vehicle.Vehicle;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false, length = 14)
    private String taxId; //CPF

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false, updatable = false)
    private LocalDateTime registrationDate;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Vehicle> vehicles = new HashSet<>();

    private boolean isActive = true;

    protected Customer() {
    }

    private Customer(Builder builder){
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.taxId = builder.taxId;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.registrationDate = builder.registrationDate;
        this.address = builder.address;
        this.vehicles = builder.vehicles;
    }

    public boolean isActive() {
        return isActive;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTaxId() {
        return taxId;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public Address getAddress() {
        return address;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void toggleStatus(){
        this.isActive = !this.isActive;
    }

    public static class Builder{

        private String firstName;
        private String lastName;
        private String taxId;
        private String email;
        private String phoneNumber;
        private LocalDateTime registrationDate;
        private Address address;
        private Set<Vehicle> vehicles = new HashSet<>();

        public Builder personalInfo(String firstName, String lastName, String taxId, String email ,String phoneNumber){
            this.firstName = firstName;
            this.lastName = lastName;
            this.taxId = taxId;
            this.email = email;
            this.phoneNumber = phoneNumber;
            return  this;
        }

        public Builder withAddress(Address address){
            this.address = address;
            return  this;
        }

        public Builder addVehicle(Vehicle vehicle){
            this.vehicles.add(vehicle);
            return this;
        }

        public Customer build(){
            if (this.taxId == null || this.taxId.isEmpty()) {
                throw new IllegalArgumentException("Não é possível criar um Customer sem o CPF (taxId)!");
            }
            if(this.registrationDate == null) this.registrationDate = LocalDateTime.now();
            return new Customer(this);
        }
    }
}
