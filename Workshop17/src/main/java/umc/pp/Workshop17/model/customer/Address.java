package umc.pp.Workshop17.model.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    @Column(nullable = false)
    private String street;
    @Column(nullable = false, length = 10)
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    @Column(nullable = false, length = 2)
    private String state;
    @Column(nullable = false, length = 9)
    private String zipCode;

    protected Address() {
    }

    private Address(Builder builder){
        this.street = builder.street;
        this.number = builder.number;
        this.complement = builder.complement;
        this.neighborhood = builder.neighborhood;
        this.city = builder.city;
        this.state = builder.state;
        this.zipCode = builder.zipCode;
    }


    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public static class Builder{

        private String street;
        private String number;
        private String complement;
        private String neighborhood;
        private String city;
        private String state;
        private String zipCode;


        public Builder streetInfo(String street,String number,String complement){
            this.street = street;
            this.number = number;
            this.complement = complement;
            return this;
        }


        public Builder location(String neighborhood,String city,String state,String zipCode){
            this.neighborhood = neighborhood;
            this.city = city;
            this.state = state;
            this.zipCode = zipCode;
            return this;
        }

        public Address build(){
            if(this.street == null || this.street.isBlank() ||
                    this.zipCode == null || this.zipCode.isBlank()){
                throw new IllegalStateException("Address construction failed: Street and ZIP Code are mandatory fields.");
            }
            return new Address(this);
        }
    }
}
