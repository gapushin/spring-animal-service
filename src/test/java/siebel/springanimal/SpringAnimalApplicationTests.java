package siebel.springanimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.ActiveProfiles;
import siebel.springanimal.animal.*;
import siebel.springanimal.animal.exceptions.InvalidAnimalException;
import siebel.springanimal.animal.repository.AnimalsRepositoryImpl;
import siebel.springanimal.animal.search.SearchServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class SpringAnimalApplicationTests {
    @Bean
    @Scope("prototype")
    public CreateAnimalService createAnimalService() {
        return new CreateAnimalService();
    }

    @Autowired
    private ResultReader resultReader;

    @Autowired
    private AnimalsRepositoryImpl animalsRepository;

    @Autowired
    CreateAnimalService createAnimalService;

    @Autowired
    SearchServiceImpl searchService;


    @Test
    @DisplayName("resultReader работает корректно логирует дату")
    void contextLoads() {
        resultReader.logAnimalDataFromFile("src/main/resources/results/findOlderAnimals.json");
        System.out.println("— — — — — — — — — —");

        resultReader.logFileStringsCount("src/main/resources/animals/logData.txt");
    }

    @Test
    @DisplayName("createAnimalService создает заданное количество животных")
    void createAnimals() {
        Map<String, List<Animal>> animals = createAnimalService.createAnimals(100, null);
        System.out.println("Animals size" + animals.values()
                .stream()
                .mapToInt(List::size)
                .sum());
        assert (animals.values().stream().mapToInt(List::size).sum() == 100);
    }

    @Test
    @DisplayName("createAnimals создает 10 животных")
    void defaultCreateAnimals() {
        Map<String, List<Animal>> animals = createAnimalService.createAnimals(null);
        System.out.println("Animals size" + animals.values()
                .stream()
                .mapToInt(List::size)
                .sum());
        assert (animals.values().stream().mapToInt(List::size).sum() == 10);
    }

    @Test
    @DisplayName("createAnimalService падает в ошибку если не передать корректное значение length")
    void createAnimalsThrows() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> createAnimalService.createAnimals(-1, null)
        );
        assertTrue(exception.getMessage().contains("Значение length должно быть > 0"));
    }

    @Test
    @DisplayName("createAnimalService падает в ошибку если указать некоректный пусть к файлу")
    void createAnimalsPathThrows() {
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> createAnimalService.createAnimals(10, "wrong/path")
        );
        assertTrue(exception.getMessage().contains("Ошибка при записи в файл"));
    }

    @Test
    @DisplayName("Проверка метода AnimalsRepositoryImpl, в список попадают только животные родившиеся в високосный год")
    void findLeapYearNamesTest() {
        List<Animal> animals = new ArrayList<>();

        animals.add(new Cat("Порода 1", "Имя 1", "Характер 1", 40.0, LocalDate.of(2000, 1, 1)));
        animals.add(new Cat("Порода 2", "Имя 2", "Характер 2", 50.0, LocalDate.of(2004, 1, 1)));
        animals.add(new Cat("Порода 2", "Имя 3", "Характер 2", 30.0, LocalDate.of(2003, 1, 1)));
        animals.add(new Cat("Порода 2", "Имя 4", "Характер 2", 30.0, LocalDate.of(2003, 1, 1)));
        animals.add(new Cat("Порода 2", "Имя 5", "Характер 2", 30.0, LocalDate.of(2003, 1, 1)));
        animals.add(new Dog("Порода 3", "Имя 3", "Характер 3", 20.0, LocalDate.of(1988, 1, 1)));
        animals.add(new Dog("Порода 3", "Имя 3", "Характер 3", 30.0, LocalDate.of(1988, 1, 1)));
        animals.add(new Dog("Порода 5", "Имя 5", "Характер 5", 30.0, LocalDate.of(1993, 1, 1)));

        Map<String, LocalDate> animalsMap = animalsRepository.findLeapYearNames(animals);
        Map<String, LocalDate> target = new HashMap<>();

        System.out.println(animalsMap);
        target.put("Cat " + "Имя 1 ", LocalDate.of(2000, 1, 1));
        target.put("Dog " + "Имя 3 ", LocalDate.of(1988, 1, 1));
        target.put("Cat " + "Имя 2 ", LocalDate.of(2004, 1, 1));

        assertTrue(animalsMap.values().containsAll(target.values()) && animalsMap.size() == target.size());
    }

    @Test
    @DisplayName("Проверка метода findOlderAnimal, в список попадают только животные старше 25 лет")
    void findOlderAnimalTest() {
        List<Animal> animals = new ArrayList<>();

        animals.add(new Cat("Порода 1", "Имя 1", "Характер 1", 40.0, LocalDate.of(2000, 1, 1)));
        animals.add(new Cat("Порода 2", "Имя 2", "Характер 2", 50.0, LocalDate.of(2004, 1, 1)));
        animals.add(new Cat("Порода 2", "Имя 3", "Характер 2", 30.0, LocalDate.of(2003, 1, 1)));
        animals.add(new Cat("Порода 2", "Имя 4", "Характер 2", 30.0, LocalDate.of(2003, 1, 1)));
        animals.add(new Cat("Порода 2", "Имя 5", "Характер 2", 30.0, LocalDate.of(2003, 1, 1)));
        animals.add(new Dog("Порода 3", "Имя 3", "Характер 3", 20.0, LocalDate.of(1988, 1, 1)));
        animals.add(new Dog("Порода 3", "Имя 3", "Характер 3", 30.0, LocalDate.of(1988, 1, 1)));
        animals.add(new Dog("Порода 5", "Имя 5", "Характер 5", 30.0, LocalDate.of(1993, 1, 1)));

        Map<Animal, Integer> map = animalsRepository.findOlderAnimal(animals, 25);
        Map<Animal, Integer> target = new HashMap<>();

        System.out.println(map);
        target.put(animals.get(7), 1993);
        target.put(animals.get(6), 1988);
        target.put(animals.get(5), 1988);

        assertTrue(map.values().containsAll(target.values()) && map.size() == target.size());
    }

    @Test
    @DisplayName("SearchServiceImpl.checkLeapYearAnimal падает в ошибку если в него не передано животное")
    void checkLeapYearAnimalThrows() {
        InvalidAnimalException exception = assertThrows(
                InvalidAnimalException.class,
                () -> searchService.checkLeapYearAnimal(null)
        );
        assertTrue(exception.getMessage().contains("На вход пришел не корректный объект животного"));
    }

}
