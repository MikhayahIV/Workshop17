package umc.pp.Workshop17.model.service;

import jakarta.persistence.*;
import umc.pp.Workshop17.model.vehicle.Vehicle;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_entry_check_list")
public class EntryCheckList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer entryMileage;

    private String fuelLevel; // E, 1/4 2/4 3/4 4/4
    private boolean hasScratches;
    private boolean hasDents;
    private boolean hasSpareTire;
    private boolean hasPersonalItem;
    private boolean functionalHeadLine; //farois funcionando
    private boolean hasLugWrench; //Chave de roda
    private String itemsLeftInVehicle; // detalhar o que foi deixado
    private String tireCondition; /// bom, regular, careca

    @Column(nullable = false)
    private String inspectorName; // Quem fez a vistoria

    @Column(nullable = false)
    private LocalDateTime inspectionDate;

    @Column(columnDefinition = "TEXT")
    private String generalNote;

    @ManyToOne
    @JoinColumn(name = "vehicle_id",nullable = false)
    private Vehicle vehicle;

    protected EntryCheckList(){

    }

    private EntryCheckList(Builder builder){
        this.entryMileage = builder.entryMileage;
        this.fuelLevel = builder.fuelLevel;
        this.hasScratches = builder.hasScratches;
        this.hasDents = builder.hasDents;
        this.hasSpareTire = builder.hasSpareTire;
        this.hasPersonalItem = builder.hasPersonalItem;
        this.functionalHeadLine = builder.functionalHeadLine;
        this.hasLugWrench = builder.hasLugWrench;
        this.itemsLeftInVehicle = builder.itemsLeftInVehicle;
        this.tireCondition = builder.tireCondition;
        this.inspectorName = builder.inspectorName;
        this.inspectionDate = builder.inspectionDate;
        this.generalNote = builder.generalNote;
        this.vehicle = builder.vehicle;
    }

    public Long getId() {
        return id;
    }

    public Integer getEntryMileage() {
        return entryMileage;
    }

    public String getFuelLevel() {
        return fuelLevel;
    }

    public boolean isHasScratches() {
        return hasScratches;
    }

    public boolean isHasDents() {
        return hasDents;
    }

    public boolean isHasSpareTire() {
        return hasSpareTire;
    }

    public boolean isHasPersonalItem() {
        return hasPersonalItem;
    }

    public boolean isFunctionalHeadLine() {
        return functionalHeadLine;
    }

    public boolean isHasLugWrench() {
        return hasLugWrench;
    }

    public String getItemsLeftInVehicle() {
        return itemsLeftInVehicle;
    }

    public String getTireCondition() {
        return tireCondition;
    }

    public String getInspectorName() {
        return inspectorName;
    }

    public LocalDateTime getInspectionDate() {
        return inspectionDate;
    }

    public String getGeneralNote() {
        return generalNote;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public static class Builder{
        private Integer entryMileage;
        private String fuelLevel;
        private boolean hasScratches;
        private boolean hasDents;
        private boolean hasSpareTire;
        private boolean hasPersonalItem;
        private boolean functionalHeadLine;
        private boolean hasLugWrench;
        private String itemsLeftInVehicle;
        private String tireCondition;
        private String inspectorName;
        private LocalDateTime inspectionDate;
        private String generalNote;
        private Vehicle vehicle;

        public Builder vehicleInfo(Vehicle vehicle,Integer mileage,String fuel,String inspector){
            this.vehicle = vehicle;
            this.entryMileage = mileage;
            this.fuelLevel = fuel;
            this.inspectorName = inspector;
            return this;
        }

        public Builder damageAndItems(boolean scratches,boolean dents,boolean personalItem,String itemsDetail){
            this.hasScratches = scratches;
            this.hasDents = dents;
            this.hasPersonalItem = personalItem;
            this.itemsLeftInVehicle = itemsDetail;
            return this;
        }

        public Builder technicalCheck(boolean headline,boolean spareTire,boolean lugWrench,String tires){
            this.functionalHeadLine = headline;
            this.hasSpareTire = spareTire;
            this.hasLugWrench = lugWrench;
            this.tireCondition = tires;
            return this;
        }

        public Builder notes(String note){
            this.generalNote = note;
            return this;
        }

        public EntryCheckList build(){
            if(this.inspectionDate == null) this.inspectionDate = LocalDateTime.now();
            if(this.vehicle == null || this.entryMileage == null || this.inspectorName == null){
                throw new IllegalStateException("Missing mandatory inspection data (Vehicle, Mileage or Inspector).");
            }
            return new EntryCheckList(this);
        }
    }
}
