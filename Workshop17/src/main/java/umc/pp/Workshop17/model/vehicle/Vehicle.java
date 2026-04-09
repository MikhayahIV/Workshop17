package umc.pp.Workshop17.model.vehicle;

import jakarta.persistence.*;
import umc.pp.Workshop17.model.customer.Customer;

import java.util.UUID;

@Entity
@Table(name = "tb_vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(unique = true, nullable = false, length = 8)
    private String licensePlate;

    @Column(nullable = false)
    private String model;

    private String brand;
    private Integer manufactureYear;
    private String color;

    @Column(unique = true, length = 17)
    private String vin; //Chassis(Número de identificação do veículo)

    private String fuelType; // Gasoline , flex, Diesel, Electric
    private String engineVersion; // 1.0, 2.0
    private String transmissionVersion; // manual,Automático
    private Integer cylinderCount; // 3 cilindros, etc

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer owner;

    protected Vehicle(){}

    private Vehicle(Builder builder) {
        this.uuid = builder.uuid;
        this.licensePlate = builder.licensePlate;
        this.model = builder.model;
        this.brand = builder.brand;
        this.manufactureYear = builder.manufactureYear;
        this.color = builder.color;
        this.vin = builder.vin;
        this.fuelType = builder.fuelType;
        this.engineVersion = builder.engineVersion;
        this.transmissionVersion = builder.transmissionVersion;
        this.cylinderCount = builder.cylinderCount;
        this.owner = builder.owner;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public Integer getManufactureYear() {
        return manufactureYear;
    }

    public String getColor() {
        return color;
    }

    public String getVin() {
        return vin;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getEngineVersion() {
        return engineVersion;
    }

    public String getTransmissionVersion() {
        return transmissionVersion;
    }

    public Integer getCylinderCount() {
        return cylinderCount;
    }

    public Customer getOwner() {
        return owner;
    }

    public void updateTechnicalData(String brand, String model, String licensePlate, Integer manufacturingYear,
                                    String color, String vin, String fuel, String engineVersion,
                                    String transmissionType, Integer cylinderCount) {
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.manufactureYear = manufacturingYear;
        this.color = color;
        this.vin = vin;
        this.fuelType = fuel;
        this.engineVersion = engineVersion;
        this.transmissionVersion = transmissionType;
        this.cylinderCount = cylinderCount;
    }

    public static class Builder{
        private UUID uuid;
        private String licensePlate;
        private String model;
        private String brand;
        private Integer manufactureYear;
        private String color;
        private String vin;
        private String fuelType;
        private String engineVersion;
        private String transmissionVersion;
        private Integer cylinderCount;
        private Customer owner;

        public Builder basicInfo(String brand,String model,String plate, Integer year, String color){
            this.brand = brand;
            this.model = model;
            this.manufactureYear = year;
            this.color = color;
            this.licensePlate = plate;
            return this;
        }

        public Builder technicalDetails(String vin, String fuel,String engine,String transmission, Integer cylinder){
            this.vin = vin;
            this.fuelType = fuel;
            this.engineVersion = engine;
            this.transmissionVersion = transmission;
            this.cylinderCount = cylinder;
            return  this;
        }

        public Builder forOwner(Customer owner){
            this.owner = owner;
            return this;
        }

        public Builder withId(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Vehicle build(){
            if(this.licensePlate == null || this.licensePlate.isBlank() ||
                    this.owner == null){
                throw new IllegalStateException("Vehicle must have a license plate and an owner.");
            }
            return new Vehicle(this);
        }
    }
}