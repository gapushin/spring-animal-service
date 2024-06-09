package siebel.springanimal.animal.exceptions;

import java.time.LocalDate;

public class InvalidAnimalException extends RuntimeException {
    public InvalidAnimalException (String message) {
        super(message);
    }

    public static InvalidAnimalException emptyObject () {
        return new InvalidAnimalException("На вход пришел не корректный объект животного " + LocalDate.now());
    }
}
