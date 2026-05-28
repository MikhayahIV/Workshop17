# 🔧 Workshop Management System (Sistema de Gestão de Oficina)

Sistema full-stack robusto para o gerenciamento de oficinas mecânicas, focado em integridade de dados e processos de engenharia de software.

## 🏛️ Arquitetura do Sistema

O projeto utiliza **Layered Architecture** (Arquitetura em Camadas), garantindo separação de responsabilidades:

1.  **Web/Controller Layer:** Endpoints REST para comunicação com o Frontend.
2.  **Service Layer:** Orquestração da lógica de negócio.
3.  **Domain Layer (Rich Domain):** Entidades inteligentes que validam seus próprios estados.
4.  **Repository Layer:** Persistência via Spring Data JPA.

---

## 💎 Diferenciais Técnicos

* **Rich Domain Model:** Entidades com comportamentos internos.
* **Value Objects (VO):** Classe `Address` como `@Embeddable`, garantindo imutabilidade.
* **Static Builder Pattern:** Garante que nenhum objeto seja instanciado em estado inconsistente.
* **Integridade de Dados:** UUIDs para identificadores externos e IDs sequenciais para registros operacionais.

---

## 📊 Modelagem de Dados (Entidades JPA)
classDiagram
    class Customer {
        -UUID uuid
        -String firstName
        -String lastName
        -String taxId
        -String email
        -String phoneNumber
        -LocalDateTime registrationDate
        -Address address
        -boolean isActive
        -Set<Vehicle> vehicles
        +builder()CustomerBuilder$
        +void toggleStatus()
        +void updatePersonalData(String firstName, String lastName, String phone, String email)
        +void changeAddress(Address newAddress)
    }
    class CustomerBuilder{
        -String firstName
        -String lastName
        -String taxId
        -String email
        -String phoneNumber
        -LocalDateTime registrationDate
        -Address address
        -boolean isActive
        -Set<Vehicle> vehicles
        +Builder withAddress(Address address)
        +Builder addVehicle(Vehicle vehicle)
        +Customer build()
    }

    class Address {
        -String street
        -String number
        -String complement
        -String neighborhood
        -String city
        -String state
        -String zipCode
        +builder()AddressBuilder$
    }

    class AddressBuilder{
        -String street
        -String number
        -String complement
        -String neighborhood
        -String city
        -String state
        -String zipCode
        +Builder streetInfo(String street,String number,String complement)
        +Builder location(String neighborhood,String city,String state,String zipCode)
        +Address build()
    }

    class Vehicle {
        -UUID uuid
        -String licensePlate
        -String model
        -String brand
        -Integer manufactureYear
        -String color
        -String vin
        -String fuelType
        -String engineVersion
        -String transmissionVersion
        -Integer cylinderCount
        -Customer owner
        +builder()VehicleBuilder$
        +void updateTechnicalData(String brand, String model, String licensePlate,Integer manufacturingYear,String color, String vin, String fuel, String engineVersion,String transmissionType, Integer cylinderCount)
        +void newOwner(Customer owner)
    }
    class vehicleBuilder{
        -String licensePlate
        -String model
        -String brand
        -Integer manufactureYear
        -String color
        -String vin
        -String fuelType
        -String engineVersion
        -String transmissionVersion
        -Integer cylinderCount
        -Customer owner

        +Builder basicInfo(String brand,String model,String plate, Integer year, String color)
        +Builder technicalDetails(String vin, String fuel,String engine,String transmission, Integer cylinder)
    +Builder forOwner(Customer owner)
    +Builder withId(UUID uuid)
    +Vehicle build()
    }

    class Mechanic {
        -UUID uuid
        -String firstName
        -String lastName
        -String taxId
        -String specialty
        -String certificationLevel
        -String employeeId
        -LocalDate hireDate
        -boolean isActive
        +builder()MechanicBuilder$
        +void toggleStatus()
        +void updatePersonalData(String firstName, String lastName, String phone, String email)
        +void updateExpertise(String specialty, String certificationLevel )
    }

    class MechanicBuilder{
        -String firstName
        -String lastName
        -String taxId
        -String specialty
        -String certificationLevel
        -String employeeId
        -LocalDate hireDate
        -boolean isActive
        +Builder personalInfo(String firstName, String lastName,String taxId,String email,String phone)
        +Builder professionalInfo(String employeeId,String specialty, String level, LocalDate hireDate)
        +Mechanic build()
    }

    class EntryCheckList {
        -Long id
        -Integer entryMileage
        -String fuelLevel
        -boolean hasScratches
        -boolean hasDents
        -boolean hasSpareTire
        -boolean hasPersonalItem
        -boolean functionalHeadLine
        -boolean hasLugWrench
        -String itemsLeftInVehicle
        -String tireCondition
        -String inspectorName
        -LocalDateTime inspectionDate
        -String generalNote
        -Vehicle vehicle
        + builder()EntryCheckListBuilder$
        + void updateNotes(String generalNote, String itemsLeft) 
    }

    clasee EntryCheckListBuilder{
        -Integer entryMileage
        -String fuelLevel
        -boolean hasScratches
        -boolean hasDents
        -boolean hasSpareTire
        -boolean hasPersonalItem
        -boolean functionalHeadLine
        -boolean hasLugWrench
        -String itemsLeftInVehicle
        -String tireCondition
        -String inspectorName
        -LocalDateTime inspectionDate
        -String generalNote
        -Vehicle vehicle
        + Builder vehicleInfo(Vehicle vehicle,Integer mileage,String fuel,String inspector)
        + Builder damageAndItems(boolean scratches,boolean dents,boolean personalItem,String itemsDetail)
        +  Builder technicalCheck(boolean headline,boolean spareTire,boolean lugWrench,String tires)
        + Builder notes(String note)
        + EntryCheckList build()
    }

    class ServiceOrder {
        -Long id
        -String protocolNumber
        -String customerComplaint
        -String mechanicDiagnostic
        -ServiceOrderStatus status
        -BigDecimal partsValue
        -BigDecimal laborValue
        -BigDecimal totalValue
        -LocalDateTime entryDate
        -LocalDateTime estimatedDeliveryDate
        -LocalDateTime finishDate
        -Customer customer
        -Vehicle vehicle
        -Mechanic mechanic
        -EntryCheckList entryChecklist
        + build()ServiceOrder$
        + updateExecutionDetails(String diagnostic, BigDecimal parts, BigDecimal labor, PricingCalculator pricingCalculator)
        + void calculateTotal()
        + void finishOS()
        + void cancelOS()
        + void updateEstimatedDate(LocalDateTime newDate)
    }

    class ServiceOrderBuilde{
        -String protocolNumber
        -String customerComplaint
        -String mechanicDiagnostic
        -ServiceOrderStatus status
        -BigDecimal partsValue
        -BigDecimal laborValue
        -BigDecimal totalValue
        -LocalDateTime entryDate
        -LocalDateTime estimatedDeliveryDate
        -LocalDateTime finishDate
        -Customer customer
        -Vehicle vehicle
        -Mechanic mechanic
        -EntryCheckList entryChecklist

        + Builder initiate(Customer customer, Vehicle vehicle, String complaint, String protocolNumber)
        + Builder withChecklist(EntryCheckList checklist)
        + Builder assignMechanic(Mechanic mechanic)
        + Builder setEstimates(LocalDateTime estimatedDate)
        + ServiceOrder build()
    }

    Customer *-- Address : @Embedded
    Customer "1" -- "0..*" Vehicle : @OneToMany
    Vehicle "1" -- "0..*" EntryCheckList : @ManyToOne
    ServiceOrder "1" -- "1" EntryCheckList : @OneToOne
    ServiceOrder "0..*" -- "1" Customer : @ManyToOne
    ServiceOrder "0..*" -- "1" Vehicle : @ManyToOne
    ServiceOrder "0..*" -- "1" Mechanic : @ManyToOne

    
---

## 🚀 Tecnologias

| Camada | Tecnologias |
| :--- | :--- |
| **Backend** | Java 21, Spring Boot 3, Spring Data JPA, Spring Web ,H2/PostgreSQL |
| **Frontend** | React, TypeScript, Vite, TanStack Query, Zod, Tailwind CSS |

---

## ⚙️ Como Executar

### **Backend**
```bash
cd backend
./mvnw spring-boot:run
```

🛠️ Funcionalidades
✅ Gestão de Clientes: Cadastro com VO Address e validação de CPF.

✅ Gestão de Veículos: Controle técnico (VIN, Placa, Motorização).

✅ Check-list de Entrada: Vistoria detalhada (Km, combustível, avarias).

✅ Ordens de Serviço: Fluxo completo (Diagnóstico, Valores e Finalização).
