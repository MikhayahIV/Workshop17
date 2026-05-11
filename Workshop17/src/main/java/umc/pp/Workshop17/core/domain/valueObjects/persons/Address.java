package umc.pp.Workshop17.core.domain.valueObjects.persons;

public record Address(

        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String zipCode
){

    public Address{
        if(street == null || street.isBlank()){
            throw new IllegalArgumentException("Invalid Street");
        }

        if(number == null || number.isBlank()){
            throw new IllegalArgumentException("invalid number");
        }

        if(neighborhood == null || neighborhood.isBlank()){
            throw new IllegalArgumentException("invalid neighborhood");
        }

        if(city == null || city.isBlank()){
            throw new IllegalArgumentException("invalid city");
        }

        if(state == null || state.isBlank()){
            throw new IllegalArgumentException("invalid state");
        }

        zipCode = zipCode.replaceAll("\\D", "");

        if(!zipCode.matches("\\d{8}")){
            throw new IllegalArgumentException("invalid ZipCode");
        }
    }
}