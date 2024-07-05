package siebel.springanimal.animal.repository;


import siebel.springanimal.animal.Animal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AnimalRepository {
    Map<String, LocalDate> findLeapYearNames (List<Animal> animals);
    Map<Animal, Integer> findOlderAnimal (List<Animal> animals, Integer minAge);
    Map<String, Integer> findDuplicate (List<Animal> animals);
    double findAverageAge (List<Animal> animals);
    List<Animal> findOldAndExpensive (List<Animal> animals);
    List<String> findMinConstAnimals (List<Animal> animals);
}
