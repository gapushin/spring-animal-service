package siebel.springanimal.animal.search;


import siebel.springanimal.animal.Animal;
import siebel.springanimal.animal.exceptions.InvalidAnimalBirthDateException;
import siebel.springanimal.animal.exceptions.InvalidAnimalException;

public interface SearchService {
    boolean checkLeapYearAnimal (Animal animal) throws InvalidAnimalException, InvalidAnimalBirthDateException;
}
