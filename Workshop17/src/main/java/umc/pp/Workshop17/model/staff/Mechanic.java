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

    private Mechanic(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.taxId = builder.taxId;
        this.email = builder.email;
        this.phone = builder.phone;
        this.employeeId = builder.employeeId;
        this.specialty = builder.specialty;
        this.certificationLevel = builder.certificationLevel;
        this.hireDate = builder.hireDate;
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

    public void toggleStatus(){
        this.isActive = !this.isActive;
    }

    public void updatePersonalData(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public void updateExpertise(String specialty, String certificationLevel ) {
        this.specialty = specialty;
        this.certificationLevel = certificationLevel;
    }

    public static class Builder{
        private String firstName;
        private String lastName;
        private String taxId;
        private String email;
        private String phone;
        private String employeeId;
        private String specialty;
        private String certificationLevel;
        private LocalDate hireDate;

        public Builder personalInfo(String firstName, String lastName,String taxId,String email,String phone){
            this.firstName = firstName;
            this.lastName = lastName;
            this.taxId = taxId;
            this.email = email;
            this.phone = phone;
            return this;
        }

        public Builder professionalInfo(String employeeId,String specialty, String level, LocalDate hireDate){
            this.employeeId = employeeId;
            this.specialty = specialty;
            this.certificationLevel = level;
            this.hireDate = hireDate;
            return  this;
        }

        public Mechanic build(){
            if(this.hireDate == null) this.hireDate = LocalDate.now();
            if(this.employeeId == null || this.employeeId.isBlank() || this.taxId == null || this.taxId.isBlank()){
                throw  new IllegalStateException("Mechanic must have an Employee ID and Tax ID.");
            }
            return new Mechanic(this);
        }

    }
}
