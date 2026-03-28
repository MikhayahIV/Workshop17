package umc.pp.Workshop17.model.customer;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Vehicle> vehicles = new HashSet<>();

    protected Customer() {
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

    public static class Builder{
        private Customer customer = new Customer();

        public Builder personalInfo(String firstName, String lastName, String taxId, String email ,String phoneNumber){
            customer.firstName = firstName;
            customer.lastName = lastName;
            customer.taxId = taxId;
            customer.email = email;
            customer.phoneNumber = phoneNumber;
            return  this;
        }

        public Builder withAddress(Address address){
            customer.address = address;
            return  this;
        }

        public Builder addVehicle(Vehicle vehicle){
            customer.getVehicles().add(vehicle);
            return this;
        }

        public Customer build(){
            if(customer.registrationDate == null) customer.registrationDate = LocalDateTime.now();
            return customer;
        }
    }
}
