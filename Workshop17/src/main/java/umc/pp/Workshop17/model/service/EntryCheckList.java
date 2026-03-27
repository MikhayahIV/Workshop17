package umc.pp.Workshop17.model.service;

import java.time.LocalDateTime;

public class EntryCheckList {
    private Long id;
    private Integer fuelLevel;
    private boolean hasScratches;
    private boolean hasSpareTire;
    private boolean functionalHeadLine;
    private boolean hasLugWrench; //Chave de roda
    private String tireCondition;
    private String itemsLeftInVehicle;
    private String inspectorName;
    private LocalDateTime inspectionDate;
    private String generalNote;
}
