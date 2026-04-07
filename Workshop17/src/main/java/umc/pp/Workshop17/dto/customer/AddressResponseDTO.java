package umc.pp.Workshop17.dto.customer;

public record AddressResponseDTO(

         String street,
         String number,
        String complement,
         String neighborhood,
         String city,
         String state,
         String zipCode
){}