package umc.pp.Workshop17.core.domain.valueObjects.persons;

public record Email(String value){

    private static final String EMAIL_PATTERN = "^[\\w.-]+@[\\w.-]+\\.[a-z]{2,3}$";
    public Email{
        if(value == null || value.isBlank() ){
            throw new IllegalArgumentException("invalid E-mail");
        }

        if(!value.matches(EMAIL_PATTERN)){
            throw new IllegalArgumentException("Invalid E-mail");
        }
    }
}