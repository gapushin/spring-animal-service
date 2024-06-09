package siebel.springanimal.animal;

import java.time.LocalDate;

public interface Animal {
    String getClassName();

    String getBreed();

    String getName();

    Double getCost();

    String getCharacter();

    LocalDate getBirthDate();

    void setBirthDate(LocalDate value);

    void printAnimalData();

    String getSecretInformation();

    void setSecretInformation(String value);

}
