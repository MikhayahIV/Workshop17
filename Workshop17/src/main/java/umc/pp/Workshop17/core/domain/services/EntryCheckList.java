package umc.pp.Workshop17.core.domain.services;

import umc.pp.Workshop17.core.domain.enums.FuelLevel;
import umc.pp.Workshop17.core.domain.enums.TireCondition;

import java.time.LocalDateTime;
import java.util.UUID;

public class EntryCheckList {

    private Long id;

    private Integer entryMileage;

    private FuelLevel fuelLevel;

    private TireCondition tireCondition;

    private boolean hasScratches;

    private boolean hasDents;

    private boolean hasSpareTire;

    private boolean hasPersonalItem;

    private String itemsLeftInVehicle;

    private boolean headlightsWorking;

    private boolean hasLugWrench;

    private UUID inspectorID; //mechanic id

    private LocalDateTime inspectionDate;

    private UUID vehicleID;

    private String generalNote;

    protected  EntryCheckList(){

    }

    private EntryCheckList(Builder builder){
        this.entryMileage = builder.entryMileage;
        this.fuelLevel = builder.fuelLevel;
        this.hasScratches = builder.hasScratches;
        this.hasDents = builder.hasDents;
        this.hasSpareTire = builder.hasSpareTire;
        this.hasPersonalItem = builder.hasPersonalItem;
        this.headlightsWorking = builder.headlightsWorking;
        this.hasLugWrench = builder.hasLugWrench;
        this.itemsLeftInVehicle = builder.itemsLeftInVehicle;
        this.tireCondition = builder.tireCondition;
        this.inspectorID = builder.inspectorID;
        this.inspectionDate = builder.inspectionDate;
        this.generalNote = builder.generalNote;
        this.vehicleID = builder.vehicleID;
    }

    public Long getId() {
        return id;
    }

    public Integer getEntryMileage() {
        return entryMileage;
    }

    public FuelLevel getFuelLevel() {
        return fuelLevel;
    }

    public TireCondition getTireCondition() {
        return tireCondition;
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

    public String getItemsLeftInVehicle() {
        return itemsLeftInVehicle;
    }

    public boolean isHeadlightsWorking() {
        return headlightsWorking;
    }

    public boolean isHasLugWrench() {
        return hasLugWrench;
    }

    public UUID getInspectorID() {
        return inspectorID;
    }

    public LocalDateTime getInspectionDate() {
        return inspectionDate;
    }

    public UUID getVehicleID() {
        return vehicleID;
    }

    public String getGeneralNote() {
        return generalNote;
    }

    public void updateNotes(String generalNote, String itemsLeftInVehicle){
        this.generalNote = generalNote;
        this.itemsLeftInVehicle = itemsLeftInVehicle;
    }

    public static class Builder{
        private Integer entryMileage;

        private FuelLevel fuelLevel;

        private TireCondition tireCondition;

        private boolean hasScratches;

        private boolean hasDents;

        private boolean hasSpareTire;

        private boolean hasPersonalItem;

        private String itemsLeftInVehicle;

        private boolean headlightsWorking;

        private boolean hasLugWrench;

        private UUID inspectorID;

        private LocalDateTime inspectionDate;

        private UUID vehicleID;

        private String generalNote;

        public Builder vehicleInfo(UUID vehicleID, Integer entryMileage,
                                   FuelLevel fuelLevel,UUID inspectorID){
            this.vehicleID = vehicleID;
            this.entryMileage = entryMileage;
            this.fuelLevel = fuelLevel;
            this.inspectorID = inspectorID;
            return this;
        }

        public Builder damageAndItems(boolean hasScratches,boolean hasDents,
                                      boolean hasPersonalItem, String itemsLeftInVehicle ){
            this.hasScratches = hasScratches;
            this.hasDents = hasDents;
            this.hasPersonalItem = hasPersonalItem;
            this.itemsLeftInVehicle = itemsLeftInVehicle;
            return this;
        }

        public Builder safetyCheck(boolean headlightsWorking, boolean hasSpareTire,
                                   boolean hasLughWrench, TireCondition tireCondition){
            this.headlightsWorking = headlightsWorking;
            this.hasSpareTire = hasSpareTire;
            this.hasLugWrench = hasLughWrench;
            this.tireCondition = tireCondition;
            return this;
        }

        public Builder notes(String generalNote){
            this.generalNote = generalNote;
            return this;
        }

        public EntryCheckList build(){
            if(this.inspectionDate == null)this.inspectionDate = LocalDateTime.now();
            if(this.vehicleID == null || this.entryMileage == null || this.inspectorID == null){
                throw new IllegalStateException("Missing mandatory inspection data (Vehicle, Mileage or Inspector).");
            }
            return new EntryCheckList(this);
        }
    }
}
