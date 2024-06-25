package siebel.springanimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import siebel.springanimal.animal.Animal;
import siebel.springanimal.animal.CreateAnimalService;
import siebel.springanimal.animal.ResultReader;
import siebel.springanimal.animal.repository.AnimalsRepositoryImpl;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SpringAnimalApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringAnimalApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> app(ctx);
    }

    @Bean
    @Scope("prototype")
    public CreateAnimalService createAnimalService() {
        return new CreateAnimalService();
    }

    private static void app(ApplicationContext ctx) {
        CreateAnimalService  createAnimalService = ctx.getBean("createAnimalService", CreateAnimalService.class);
        Map<String, List<Animal>> animals = createAnimalService.createAnimals(100);

        AnimalsRepositoryImpl animalsRepository = ctx.getBean("animalsRepositoryImpl", AnimalsRepositoryImpl.class);
        Map<Animal, Integer> olderAnimals = animalsRepository.findOlderAnimal(animals.get("Cat"), 5);
        System.out.println(olderAnimals.toString());

        ResultReader resultReader = ctx.getBean("resultReader", ResultReader.class);
        resultReader.logAnimalDataFromFile("src/main/resources/results/findOlderAnimals.json");
        System.out.println("— — — — — — — — — —");

        resultReader.logFileStringsCount("src/main/resources/animals/logData.txt");
    }
}
