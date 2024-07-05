package siebel.springanimal.animal;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@Component
public class ResultReader {
    private String decode(String str) {
        return new String(Base64.getDecoder().decode(str));
    }

    public void logAnimalDataFromFile(String path) throws RuntimeException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        if (path == null) {
            throw new RuntimeException("Wrong path");
        }

        File file = new File(path);

        try {
            AnimalJSON[] animals = objectMapper.readValue(file, AnimalJSON[].class);
            for (AnimalJSON animal: animals) {
                animal.setSecretInformation(decode(animal.getSecretInformation()));
                animal.printAnimalData();
            }
        } catch (StreamReadException | DatabindException e) {
            System.out.println("exception in logAnimalDataFromFile:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException in logAnimalDataFromFile: " + e.getMessage());
        }
    }

    public void logFileStringsCount(String path) {
        try {
            long count = Files.readAllLines(Paths.get(path)).size();
            System.out.println("Количество строк в файле" + path + ": " + count);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
