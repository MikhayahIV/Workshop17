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

        private Address address = new Address();

        public Builder streetInfo(String street,String number,String complement){
            address.street = street;
            address.number = number;
            address.complement = complement;
            return this;
        }


        public Builder location(String neighborhood,String city,String state,String zipcode){
            address.neighborhood = neighborhood;
            address.city = city;
            address.state = state;
            address.zipCode = zipcode;
            return this;
        }

        public Address build(){
            if(address.street == null || address.street.isBlank() ||
                    address.zipCode == null || address.zipCode.isBlank()){
                throw new IllegalStateException("Address construction failed: Street and ZIP Code are mandatory fields.");
            }
            return address;
        }
    }
}
