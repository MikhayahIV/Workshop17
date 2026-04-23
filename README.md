Parte 1: Introdução e Arquitetura
Markdown
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
Parte 2: O Diagrama (Copie o bloco abaixo)
Snippet de código
classDiagram
    class Customer {
        +UUID uuid
        +String firstName
        +String lastName
        +String taxId
        +String email
        +String phoneNumber
        +LocalDateTime registrationDate
        +Address address
        +boolean isActive
    }

    class Address {
        +String street
        +String number
        +String complement
        +String neighborhood
        +String city
        +String state
        +String zipCode
    }

    class Vehicle {
        +UUID uuid
        +String licensePlate
        +String model
        +String brand
        +Integer manufactureYear
        +String color
        +String vin
        +String fuelType
        +String engineVersion
        +String transmissionVersion
        +Integer cylinderCount
        +Customer owner
    }

    class Mechanic {
        +UUID uuid
        +String firstName
        +String lastName
        +String taxId
        +String specialty
        +String certificationLevel
        +String employeeId
        +LocalDate hireDate
        +boolean isActive
    }

    class EntryCheckList {
        +Long id
        +Integer entryMileage
        +String fuelLevel
        +boolean hasScratches
        +boolean hasDents
        +boolean hasSpareTire
        +boolean hasPersonalItem
        +boolean functionalHeadLine
        +boolean hasLugWrench
        +String itemsLeftInVehicle
        +String tireCondition
        +String inspectorName
        +LocalDateTime inspectionDate
        +String generalNote
        +Vehicle vehicle
    }

    class ServiceOrder {
        +Long id
        +String protocolNumber
        +String customerComplaint
        +String mechanicDiagnostic
        +ServiceOrderStatus status
        +BigDecimal partsValue
        +BigDecimal laborValue
        +BigDecimal totalValue
        +LocalDateTime entryDate
        +LocalDateTime estimatedDeliveryDate
        +LocalDateTime finishDate
        +Customer customer
        +Vehicle vehicle
        +Mechanic mechanic
        +EntryCheckList entryChecklist
    }

    Customer *-- Address : @Embedded
    Customer "1" -- "0..*" Vehicle : @OneToMany
    Vehicle "1" -- "0..*" EntryCheckList : @ManyToOne
    ServiceOrder "1" -- "1" EntryCheckList : @OneToOne
    ServiceOrder "0..*" -- "1" Customer : @ManyToOne
    ServiceOrder "0..*" -- "1" Vehicle : @ManyToOne
    ServiceOrder "0..*" -- "1" Mechanic : @ManyToOne

    
Parte 3: Tecnologias e Finalização
Markdown
---

## 🚀 Tecnologias

| Camada | Tecnologias |
| :--- | :--- |
| **Backend** | Java 21, Spring Boot 3, Spring Data JPA, H2/PostgreSQL |
| **Frontend** | React, TypeScript, Vite, TanStack Query, Zod, Tailwind CSS |

---

## ⚙️ Como Executar

### **Backend**
```bash
cd backend
./mvnw spring-boot:run


### **Frontend**
```bash
cd frontend
npm install
npm run dev


🛠️ Funcionalidades
✅ Gestão de Clientes: Cadastro com VO Address e validação de CPF.

✅ Gestão de Veículos: Controle técnico (VIN, Placa, Motorização).

✅ Check-list de Entrada: Vistoria detalhada (Km, combustível, avarias).

✅ Ordens de Serviço: Fluxo completo (Diagnóstico, Valores e Finalização).
