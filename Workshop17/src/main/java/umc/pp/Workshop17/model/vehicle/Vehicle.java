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

    public static class Builder{
        private Vehicle vehicle = new Vehicle();

        public Builder basicInfo(String brand,String model,String plate, Integer year, String color){
            vehicle.brand = brand;
            vehicle.model = model;
            vehicle.manufactureYear = year;
            vehicle.color = color;
            vehicle.licensePlate = plate;
            return this;
        }

        public Builder technicalDetails(String vin, String fuel,String engine,String transmission, Integer cylinder){
            vehicle.vin = vin;
            vehicle.fuelType = fuel;
            vehicle.engineVersion = engine;
            vehicle.transmissionVersion = transmission;
            vehicle.cylinderCount = cylinder;
            return  this;
        }

        public Builder forOwner(Customer owner){
            vehicle.owner = owner;
            return this;
        }

        public Vehicle build(){
            if(vehicle.licensePlate == null || vehicle.licensePlate.isBlank() ||
                    vehicle.owner == null){
                throw new IllegalStateException("Vehicle must have a license plate and an owner.");
            }
            return vehicle;
        }
    }
}