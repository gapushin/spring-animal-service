package siebel.springanimal.animal;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CreateAnimalService {
    @Autowired
    AnimalService animalService;

    private void writeAnimalDataToFile(String filePath, Animal animal, int count) {
        Path path = Paths.get(filePath);
        String animalDataString = count + " " + animal.getBreed() + " " + animal.getName() + " " + animal.getCost() + " " + animal.getBirthDate() + "\n";

        try {
            Files.writeString(path, animalDataString, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeDelimiterToFile(String filePath) {
        Path path = Paths.get(filePath);

        try {
            Files.writeString(path, "— — — — — — — END — — — — — — — \n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, List<Animal>> createAnimals(int length) {
        Map<String, List<Animal>> animalsMap = new HashMap<>();

        if (length <= 0) {
            throw new RuntimeException("Значение length должно быть > 0");
        }

        for (int i = 0; i < length; i++) {
            Animal animal = animalService.createRandomAnimal();
            writeAnimalDataToFile("src/main/resources/animals/logData.txt", animal, i + 1);

            animalsMap.computeIfAbsent(animal.getClassName(), item -> new ArrayList<>()).add(animal);
        }
        writeDelimiterToFile("src/main/resources/animals/logData.txt");
        return animalsMap;
    }

    public Map<String, List<Animal>> createAnimals() {
        int count = 0;
        Map<String, List<Animal>> animalsMap = new HashMap<>();

        do {
            Animal animal = animalService.createRandomAnimal();
            writeAnimalDataToFile("src/main/resources/animals/logData.txt", animal, count + 1);
            animalsMap.computeIfAbsent(animal.getClassName(), item -> new ArrayList<>()).add(animal);
            count++;
        } while (count < 10);

        writeDelimiterToFile("src/main/resources/animals/logData.txt");
        return animalsMap;
    }
}
