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

    public static class Builder{
        private EntryCheckList checkList = new EntryCheckList();

        public Builder vehicleInfo(Vehicle vehicle,Integer mileage,String fuel,String inspector){
            checkList.vehicle = vehicle;
            checkList.entryMileage = mileage;
            checkList.fuelLevel = fuel;
            checkList.inspectorName = inspector;
            return this;
        }

        public Builder damageAndItems(boolean scratches,boolean dents,boolean personalItem,String itemsDetail){
            checkList.hasScratches = scratches;
            checkList.hasDents = dents;
            checkList.hasPersonalItem = personalItem;
            checkList.itemsLeftInVehicle = itemsDetail;
            return this;
        }

        public Builder technicalCheck(boolean headline,boolean spareTire,boolean lugWrench,String tires){
            checkList.functionalHeadLine = headline;
            checkList.hasSpareTire = spareTire;
            checkList.hasLugWrench = lugWrench;
            checkList.tireCondition = tires;
            return this;
        }

        public Builder notes(String note){
            checkList.generalNote = note;
            return this;
        }

        public EntryCheckList build(){
            if(checkList.inspectionDate == null) checkList.inspectionDate = LocalDateTime.now();
            if(checkList.vehicle == null || checkList.entryMileage == null || checkList.inspectorName == null){
                throw new IllegalStateException("Missing mandatory inspection data (Vehicle, Mileage or Inspector).");
            }
            return checkList;
        }
    }
}
