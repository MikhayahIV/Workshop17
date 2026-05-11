package umc.pp.Workshop17.core.domain.valueObjects.persons;

public record PhoneNumber(String value) {

    private static final String PHONE_PATTERN = "^\\d{10,11}$";
    public PhoneNumber{
        if( value == null ||value.isBlank()){
            throw new IllegalArgumentException("Invalid phone number");
        }

        value = value.replaceAll("\\D", "");

        if(!value.matches(PHONE_PATTERN)){
            throw new IllegalArgumentException("Invalid Phone number");
        }
    }
}
