package siebel.springanimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import siebel.springanimal.animal.ResultReader;

@SpringBootTest
@ActiveProfiles("test")
class SpringAnimalApplicationTests {

    @Autowired
    private ResultReader resultReader;

    @Test
    void contextLoads() {
        resultReader.logAnimalDataFromFile("src/main/resources/results/findOlderAnimals.json");
        System.out.println("— — — — — — — — — —");

        resultReader.logFileStringsCount("src/main/resources/animals/logData.txt");
    }

}
