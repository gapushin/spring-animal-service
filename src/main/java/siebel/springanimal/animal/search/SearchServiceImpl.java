package siebel.springanimal.animal.search;

import org.springframework.stereotype.Component;
import siebel.springanimal.animal.Animal;
import siebel.springanimal.animal.exceptions.InvalidAnimalBirthDateException;
import siebel.springanimal.animal.exceptions.InvalidAnimalException;

@Component
public class SearchServiceImpl implements SearchService {
    @Override
    public boolean checkLeapYearAnimal (Animal animal) throws InvalidAnimalException, InvalidAnimalBirthDateException {
        if (animal == null) {
           throw InvalidAnimalException.emptyObject();
        }

        if (animal.getBirthDate() == null) {
            throw InvalidAnimalBirthDateException.emptyBirthdate(animal.getName());
        }

        final int year = animal.getBirthDate().getYear();

        return year > 1584 &&
                ((year % 400 == 0) || (year %4 == 0 && year % 100 != 0));
    }
}
