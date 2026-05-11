package umc.pp.Workshop17.core.domain.valueObjects.vehicles;

public record LicensePlate(String value) {

    private static final String OLD_PATTERN = "^[A-Z]{3}\\d{4}$";

    private static final String MERCOSUL_PATTERN = "^[A-Z]{3}\\d[A-Z]\\d{2}$";

    public LicensePlate{
        if(value == null || value.isBlank()){
            throw new IllegalArgumentException("Invalid plate");
        }

        value = value
                .replace("-", "")
                .replace("\\s","")
                .toUpperCase();

        boolean validOld = value.matches(OLD_PATTERN);
        boolean validMercosul = value.matches(MERCOSUL_PATTERN);

        if(!validOld && !validMercosul){
            throw new IllegalArgumentException("invalid plate");
        }
    }
}
