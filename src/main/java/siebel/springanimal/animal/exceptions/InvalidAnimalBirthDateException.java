package siebel.springanimal.animal.exceptions;

public class InvalidAnimalBirthDateException extends Exception {
    public InvalidAnimalBirthDateException (String message) {
        super(message);
    }

    public static InvalidAnimalBirthDateException emptyBirthdate(String type) {
        return new InvalidAnimalBirthDateException("У животного " + type + " не указана его дата рождения");
    }
}
