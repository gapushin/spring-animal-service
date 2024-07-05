package siebel.springanimal.animal;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class AnimalJSON extends AbstractAnimal {
    public AnimalJSON(
            @JsonProperty("breed") String breed,
            @JsonProperty("name") String name,
            @JsonProperty("cost") Double cost,
            @JsonProperty("character") String character,
            @JsonProperty("birthDate") LocalDate birthDate
    ) {
        super(breed, name, character, cost, birthDate);
    }
}
