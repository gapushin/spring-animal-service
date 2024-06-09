package siebel.springanimal.animal;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Random;

abstract public class AbstractAnimal implements Animal {
    protected String breed;
    protected String className;
    protected String name;
    protected Double cost;
    protected String character;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    protected LocalDate birthDate;
    protected transient String secretInformation;

    protected AbstractAnimal(String breed, String name, String character, Double cost, LocalDate birthDate) {
        this.className = "Animal";
        this.breed = breed;
        this.name = name;
        this.character = character;
        this.cost = cost;
        this.birthDate = birthDate;

        setSecretInformation(Base64.getEncoder().encodeToString(getRandomSecretFromFile().getBytes()));
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public Double getCost() {
        return cost;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCharacter() {
        return character;
    }

    @Override
    public String getBreed() {
        return breed;
    }

    @Override
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public void setBirthDate(LocalDate value) {
        birthDate = value;
    }

    public void printAnimalData () {
        System.out.println("Порода: " + getBreed() + " Характер: " + getCharacter() + " Имя: "+ getName() + " Цена: " + getCost() /*+ " Дата рождения: " + getBirthDate() *//*+  " Секрет: " + getSecretInformation()*/ + "\n");
    }

    @Override
    public String getSecretInformation() { return secretInformation; }

    public void setSecretInformation(String value) { secretInformation = value; }

    private String getRandomSecretFromFile() {
        Path path = Paths.get("src/main/resources/secretStore/secretInformation.txt");

        try {
            List<String> allLines = Files.readAllLines(path);
            return allLines.get(new Random().nextInt(allLines.size() - 1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
