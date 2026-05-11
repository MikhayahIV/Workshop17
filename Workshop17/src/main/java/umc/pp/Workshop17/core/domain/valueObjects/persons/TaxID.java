package umc.pp.Workshop17.core.domain.valueObjects.persons;

public record TaxID(String value) {

    private static final String TAXID_PATTERN = "^\\d{11}$|^\\d{14}$";

    public TaxID{
        if ( value == null || value.isBlank()){
            throw new IllegalArgumentException("Invalid TaxId");
        }

        value = value.replaceAll("\\D", "");

        if(!value.matches(TAXID_PATTERN)){
            throw new IllegalArgumentException("Invalid TaxId");
        }
    }
}
