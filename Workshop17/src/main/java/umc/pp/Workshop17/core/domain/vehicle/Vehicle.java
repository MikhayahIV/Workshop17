package umc.pp.Workshop17.core.domain.vehicle;

import umc.pp.Workshop17.core.domain.customer.Customer;
import umc.pp.Workshop17.core.domain.enums.FuelType;
import umc.pp.Workshop17.core.domain.enums.TransmissionType;
import umc.pp.Workshop17.core.domain.valueObjects.vehicles.LicensePlate;
import umc.pp.Workshop17.core.domain.valueObjects.vehicles.Vin;

import java.util.UUID;

public class Vehicle {

    private UUID id;

    private LicensePlate licensePlate;

    private String model;

    private String brand;

    private Integer manufactureYear;

    private String color;

    private Vin vin;

    private FuelType fuelType;

    private String engineVersion;

    private TransmissionType transmissionType;

    private Integer cylinderCount;

    private UUID owner;

    protected Vehicle(){}

    private Vehicle(Builder builder) {
        this.id = builder.id;
        this.licensePlate = builder.licensePlate;
        this.model = builder.model;
        this.brand = builder.brand;
        this.manufactureYear = builder.manufactureYear;
        this.color = builder.color;
        this.vin = builder.vin;
        this.fuelType = builder.fuelType;
        this.engineVersion = builder.engineVersion;
        this.transmissionType = builder.transmissionType;
        this.cylinderCount = builder.cylinderCount;
        this.owner = builder.owner;
    }

    public UUID getId() {
        return id;
    }

    public LicensePlate getLicensePlate() {
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

    public Vin getVin() {
        return vin;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public String getEngineVersion() {
        return engineVersion;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public Integer getCylinderCount() {
        return cylinderCount;
    }

    public UUID getOwner() {
        return owner;
    }

    public void updateTechnicalData(String brand, String model, LicensePlate licensePlate, Integer manufacturingYear,
                                    String color, Vin vin, FuelType fuel, String engineVersion,
                                    TransmissionType transmissionType, Integer cylinderCount) {
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.manufactureYear = manufacturingYear;
        this.color = color;
        this.vin = vin;
        this.fuelType = fuel;
        this.engineVersion = engineVersion;
        this.transmissionType = transmissionType;
        this.cylinderCount = cylinderCount;
    }

    public void changeOwner(UUID owner){
        this.owner = owner;
    }

    public static class Builder{
        private UUID id;
        private LicensePlate licensePlate;
        private String model;
        private String brand;
        private Integer manufactureYear;
        private String color;
        private Vin vin;
        private FuelType fuelType;
        private String engineVersion;
        private TransmissionType transmissionType;
        private Integer cylinderCount;
        private UUID owner;

        public Builder basicInfo(String brand, String model, LicensePlate plate, Integer year, String color){
            this.brand = brand;
            this.model = model;
            this.manufactureYear = year;
            this.color = color;
            this.licensePlate = plate;
            return this;
        }

        public Builder technicalDetails(Vin vin, FuelType fuel, String engine, TransmissionType transmission, Integer cylinder){
            this.vin = vin;
            this.fuelType = fuel;
            this.engineVersion = engine;
            this.transmissionType = transmission;
            this.cylinderCount = cylinder;
            return  this;
        }

        public Builder forOwner(UUID owner){
            this.owner = owner;
            return this;
        }

        public Builder withId(UUID uuid) {
            this.id = uuid;
            return this;
        }

        public Vehicle build(){
            if(this.licensePlate == null || this.owner == null){
                throw new IllegalStateException("Vehicle must have a license plate and an owner.");
            }
            return new Vehicle(this);
        }
    }
}
