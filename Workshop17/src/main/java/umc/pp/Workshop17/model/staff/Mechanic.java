package umc.pp.Workshop17.model.staff;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_mechanic")
public class Mechanic {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true,nullable = false, length = 14)
    private String taxId; //CPF

    private String specialty;
    private String certificationLevel;

    @Column(unique = true,nullable = false)
    private String employeeId; //Registro Funcional

    @Column(nullable = false)
    private LocalDate hireDate;

    private String phone;
    private String email;
    private boolean isActive = true;

    protected Mechanic() {
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

    public String getSpecialty() {
        return specialty;
    }

    public String getCertificationLevel() {
        return certificationLevel;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return isActive;
    }

    public static class Builder{
        private  Mechanic mechanic = new Mechanic();

        public Builder personalInfo(String firstName, String lastName,String taxId,String email,String phone){
            mechanic.firstName = firstName;
            mechanic.lastName = lastName;
            mechanic.taxId = taxId;
            mechanic.email = email;
            mechanic.phone = phone;
            return this;
        }

        public Builder professionalInfo(String employeeId,String specialty, String level, LocalDate hireDate){
            mechanic.employeeId = employeeId;
            mechanic.specialty = specialty;
            mechanic.certificationLevel = level;
            mechanic.hireDate = hireDate;
            return  this;
        }

        public Mechanic build(){
            if(mechanic.hireDate == null) mechanic.hireDate = LocalDate.now();
            if(mechanic.employeeId == null || mechanic.employeeId.isBlank() || mechanic.taxId == null || mechanic.taxId.isBlank()){
                throw  new IllegalStateException("Mechanic must have an Employee ID and Tax ID.");
            }
            return mechanic;
        }

    }
}
