package siebel.springanimal.animal.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import siebel.springanimal.animal.Animal;
import siebel.springanimal.animal.CreateAnimalService;
import siebel.springanimal.animal.exceptions.InvalidAnimalBirthDateException;
import siebel.springanimal.animal.search.SearchService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class AnimalsRepositoryImpl implements  AnimalRepository {
    private final SearchService searchService;
    private final CreateAnimalService createAnimalService;

    @PostConstruct
    private void fillStorage() {
        createAnimalService.createAnimals();
    }
    private void writeToFile(Map<Animal, Integer> animals) {
        File file = new File("src/main/resources/results/findOlderAnimals.json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        try {
            objectMapper.writeValue(file, animals.keySet());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Map<String, LocalDate> findLeapYearNames(List<Animal> animals) {
    Map<String, LocalDate> map = new HashMap<>();

    animals.stream()
        .filter(animal -> {
            try {
                return this.searchService.checkLeapYearAnimal(animal);
            } catch (InvalidAnimalBirthDateException e) {
                throw new RuntimeException(e);
            }
        })
        .forEach(animal -> map.put(String.format("%s %s ", animal.getClassName(), animal.getName()), animal.getBirthDate()));

    return  map;
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(List<Animal> animals, Integer minAge) {
        Map<Animal, Integer> map = new HashMap<>();

        animals.stream()
            .filter(animal -> animal.getBirthDate().getYear() < LocalDate.now().getYear() - minAge)
            .forEach(animal -> map.put(animal, animal.getBirthDate().getYear()));

        writeToFile(map);
        return  map;
    }

    @Override
    public Map<String, Integer> findDuplicate(List<Animal> animals) {
        Map<String, Integer> map = new HashMap<>();

        animals.forEach(animal -> map.put(animal.getClassName(), map.getOrDefault(animal.getClassName(), 0) + 1));

        return  map;
    }

    @Override
    public double findAverageAge(List<Animal> animals) {
        return animals.stream()
            .mapToInt(animal -> LocalDate.now().getYear() - animal.getBirthDate().getYear())
            .summaryStatistics()
            .getAverage();
    }

    @Override
    public List<Animal> findOldAndExpensive(List<Animal> animals) {
        Double averagePrice = animals.stream()
            .mapToDouble(Animal::getCost)
            .summaryStatistics()
            .getAverage();

        return animals.stream()
            .filter(animal -> animal.getCost() > averagePrice && animal.getBirthDate().getYear() < LocalDate.now().getYear() - 5)
            .sorted(Comparator.comparing(Animal::getBirthDate))
            .collect(Collectors.toList());
    }

    @Override
    public List<String> findMinConstAnimals(List<Animal> animals) {
        List<String> result = new ArrayList<>();

        animals
           .stream()
           .sorted(Comparator.comparing(Animal::getCost))
           .limit(3)
           .sorted(Comparator.comparing(Animal::getName).reversed())
           .toList()
           .forEach(animal -> result.add(animal.getName()));

        return result;
    }
}
