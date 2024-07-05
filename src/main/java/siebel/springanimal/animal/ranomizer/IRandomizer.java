package siebel.springanimal.animal.ranomizer;

import java.time.LocalDate;

public interface IRandomizer {
    String getRandomName();
    String getRandomBreed();
    Double getRandomCoast();
    String getRandomCharacter();
    LocalDate getRandomBirthDate();
}
