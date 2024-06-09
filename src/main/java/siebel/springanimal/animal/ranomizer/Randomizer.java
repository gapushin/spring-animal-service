package siebel.springanimal.animal.ranomizer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class Randomizer implements IRandomizer {
    private final RandomizerProperties randomizerProperties;

    private Integer getRandomIndex(int arrLength) {
        return (int)Math.floor(Math.random() * arrLength);
    }

    @Override
    public String getRandomBreed() {
        return randomizerProperties.getBreeds().get(getRandomIndex(randomizerProperties.getBreeds().size()));
    }

    @Override
    public Double getRandomCoast() {
        return randomizerProperties.getCosts().get(getRandomIndex(randomizerProperties.getCosts().size()));
    }

    @Override
    public String getRandomName() {
        return randomizerProperties.getNames().get(getRandomIndex(randomizerProperties.getNames().size()));
    }

    @Override
    public String getRandomCharacter() {
        return randomizerProperties.getCharacters().get(getRandomIndex(randomizerProperties.getCharacters().size()));
    }

    @Override
    public LocalDate getRandomBirthDate() {
        Random random = new Random();
        int year = 1970 + random.nextInt(55);
        int month = random.nextInt(11) + 1;
        int day = random.nextInt(27) + 1;

        LocalDate localDate = LocalDate.of(year, month, day);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String localDateString = localDate.format(formatter);

        return LocalDate.parse(localDateString, formatter);
    }
}

