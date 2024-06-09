package siebel.springanimal.animal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import siebel.springanimal.animal.ranomizer.Randomizer;

import java.util.*;

@Component
@RequiredArgsConstructor
public class AnimalService {
    private final Randomizer randomizer;

    public Map<String, List<Animal>> createAnimals() {
        int count = 0;
        Map<String, List<Animal>> animalsMap = new HashMap<>();

        while (count < 10) {
            Animal animal = createRandomAnimal();

            animalsMap.computeIfAbsent(animal.getClassName(), item -> new ArrayList<>()).add(animal);
            count++;
        }

        return animalsMap;
    }

    public Animal createRandomAnimal() {
        int randomValue = new Random().nextInt(4);

        return switch (randomValue) {
            case 0 ->
                    new Cat(randomizer.getRandomBreed(), randomizer.getRandomName(), randomizer.getRandomCharacter(), randomizer.getRandomCoast(), randomizer.getRandomBirthDate());
            case 1 ->
                    new Dog(randomizer.getRandomBreed(), randomizer.getRandomName(), randomizer.getRandomCharacter(), randomizer.getRandomCoast(), randomizer.getRandomBirthDate());
            case 2 ->
                    new Wolf(randomizer.getRandomBreed(), randomizer.getRandomName(), randomizer.getRandomCharacter(), randomizer.getRandomCoast(), randomizer.getRandomBirthDate());
            default ->
                    new Shark(randomizer.getRandomBreed(), randomizer.getRandomName(), randomizer.getRandomCharacter(), randomizer.getRandomCoast(), randomizer.getRandomBirthDate());
        };
    }
}
