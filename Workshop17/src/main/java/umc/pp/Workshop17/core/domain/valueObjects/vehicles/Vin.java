package umc.pp.Workshop17.core.domain.valueObjects.vehicles;

public record Vin(String  value) {

    private static final String VIN_PATTERN = "^[A-HJ-NPR-Z0-9]{17}$";

    public Vin{
        if(value == null || value.isBlank()) {
            throw new IllegalArgumentException("Invalid VIN");
        }

        value = value.trim().toUpperCase();

        if(!value.matches(VIN_PATTERN)){
            throw  new IllegalArgumentException("Invalid VIN");
        }
    }
}
