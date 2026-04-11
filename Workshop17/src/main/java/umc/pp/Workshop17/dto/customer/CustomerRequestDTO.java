package umc.pp.Workshop17.dto.customer;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import umc.pp.Workshop17.model.customer.Address;


public record CustomerRequestDTO(

         @NotBlank
         @Pattern(regexp = "^[A-Za-zÀ-ÿ ]+$", message = "O nome deve conter apenas letras")
         String firstName,

         @NotBlank
         @Pattern(regexp = "^[A-Za-zÀ-ÿ ]+$", message = "O sobrenome deve conter apenas letras")
         String lastName,


         @NotBlank
         @Pattern(regexp = "(^\\d{11}$|^\\d{14}$)", message = "O documento deve ter 11 (CPF) ou 14 (CNPJ) dígitos numéricos")
         String taxId, //CPF or CNPJ


         @Email @NotBlank String email,


         @NotBlank
         @Pattern(regexp = "^\\d{11}$", message = "O celular deve conter 11 dígitos numéricos (DDD + número)")
         String phone,


         @NotNull @Valid AddressRequestDTO address
){}