package umc.pp.Workshop17.core.domain.employee;

import umc.pp.Workshop17.core.domain.enums.CertificationLevel;
import umc.pp.Workshop17.core.domain.valueObjects.persons.Email;
import umc.pp.Workshop17.core.domain.valueObjects.persons.PhoneNumber;
import umc.pp.Workshop17.core.domain.valueObjects.persons.TaxID;

import java.time.LocalDate;
import java.util.UUID;

public class Mechanic {

    private UUID id;

    private String firstName;

    private String lastName;

    private TaxID taxID;

    private Email email;

    private PhoneNumber phoneNumber;

    private String specialty;

    private CertificationLevel certificationLevel;

    private String employeeID;

    private LocalDate hireDate;

    private boolean isActive = true;

    protected Mechanic() {
    }

    private Mechanic(Builder builder){
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.taxID = builder.taxID;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.employeeID = builder.employeeID;
        this.specialty = builder.specialty;
        this.certificationLevel = builder.certificationLevel;
        this.hireDate = builder.hireDate;
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

    public String getSpecialty() {
        return specialty;
    }

    public CertificationLevel getCertificationLevel() {
        return certificationLevel;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public boolean isActive() {
        return isActive;
    }


    public void toggleStatus(){
        this.isActive = !this.isActive;
    }

    public void updatePersonalInfo(String firstName, String lastName,
                                   PhoneNumber phoneNumber, Email email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;

    }

    public void updateExpertise(String specialty, CertificationLevel certificationLevel){
        this.specialty = specialty;
        this.certificationLevel = certificationLevel;
    }

    public static class Builder{
        private String firstName;
        private String lastName;
        private TaxID taxID;
        private Email email;
        private PhoneNumber phoneNumber;
        private String specialty;
        private CertificationLevel certificationLevel;
        private String employeeID;
        private LocalDate hireDate;

        public Builder personalInfo(String firstName, String lastName, TaxID taxID,
                                   Email email, PhoneNumber phoneNumber){
            this.firstName = firstName;
            this.lastName = lastName;
            this.taxID = taxID;
            this.email = email;
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder professionalInfo(String employeeID, String specialty,
                                        LocalDate hireDate, CertificationLevel certificationLevel){
            this.employeeID = employeeID;
            this.specialty = specialty;
            this.hireDate = hireDate;
            this.certificationLevel = certificationLevel;
            return this;
        }

        public Mechanic build(){
            if(this.hireDate == null) this.hireDate = LocalDate.now();
            if(this.employeeID == null || this.employeeID.isBlank() || this.taxID == null){
                throw new IllegalArgumentException("Mechanic must have an Employee ID and Tax ID.");
            }
            return new Mechanic(this);
        }
    }
}
