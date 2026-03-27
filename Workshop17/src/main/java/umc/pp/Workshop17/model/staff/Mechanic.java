package umc.pp.Workshop17.model.staff;

import java.time.LocalDate;
import java.util.UUID;

public class Mechanic {
    private UUID uuid;
    private String name;
    private String taxId; //CPF
    private String especiality;
    private String employeeId; //Registro Funcional
    private LocalDate hireDate;
    private String phone;
    private String email;
    private boolean isActive;
}
