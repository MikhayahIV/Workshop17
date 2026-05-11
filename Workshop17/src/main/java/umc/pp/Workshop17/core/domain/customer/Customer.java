package umc.pp.Workshop17.core.domain.customer;

import umc.pp.Workshop17.core.domain.valueObjects.persons.Address;
import umc.pp.Workshop17.core.domain.valueObjects.persons.Email;
import umc.pp.Workshop17.core.domain.valueObjects.persons.PhoneNumber;
import umc.pp.Workshop17.core.domain.valueObjects.persons.TaxID;
import umc.pp.Workshop17.exception.BusinessException;

import java.time.LocalDateTime;
import java.util.UUID;

public class Customer {

    private UUID id;

    private String firstName;

    private String lastName;

    private TaxID taxID;

    private Email email;

    private PhoneNumber phoneNumber;

    private LocalDateTime registrationDate;

    private Address address;

    private boolean isActive = true;

    protected Customer() {
    }

    private Customer(Builder builder){
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.taxID = builder.taxID;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.registrationDate = builder.registrationDate;
        this.address = builder.address;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public TaxID getTaxID() {
        return taxID;
    }

    public Email getEmail() {
        return email;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public Address getAddress() {
        return address;
    }

    public boolean isActive() {
        return isActive;
    }

    public void toggleStatus(){
        this.isActive = !this.isActive;
    }

    public void updatePersonalData(String firstName, String lastName, PhoneNumber phone, Email email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phone;
        this.email = email;
    }

    public void changeAddress(Address newAddress) {
        if (newAddress == null) throw new BusinessException("Endereço não pode ser nulo.");
        this.address = newAddress;
    }

    public static class Builder{
        private String firstName;
        private String lastName;
        private TaxID taxID;
        private Email email;
        private PhoneNumber phoneNumber;
        private LocalDateTime registrationDate;
        private Address address;

        public Builder personalInfo(String firstName, String lastName,
                                    TaxID taxId, Email email ,
                                    PhoneNumber phoneNumber){
            this.firstName = firstName;
            this.lastName = lastName;
            this.taxID = taxId;
            this.email = email;
            this.phoneNumber = phoneNumber;
            return  this;
        }

        public Builder withAddress(Address address){
            this.address = address;
            return  this;
        }


        public Customer build(){
            if (this.taxID == null) {
                throw new IllegalArgumentException("Customer must have a TaxID");
            }
            if(this.registrationDate == null) this.registrationDate = LocalDateTime.now();
            return new Customer(this);
        }
    }
}
