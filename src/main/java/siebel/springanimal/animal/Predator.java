package siebel.springanimal.animal;

import java.time.LocalDate;

public class Predator extends AbstractAnimal {
    public Predator(
            String breed,
            String name,
            String character,
            Double cost,
            LocalDate birthDate
    ) {
        super(breed, name, character, cost, birthDate);
        this.className = "Predator";
    }
    
    @Override
    public void printAnimalData () {
        System.out.println("Порода: " + getBreed() + " Характер: " + getCharacter() + " Имя: "+ getName() + " Цена: " + getCost() + " Дата рождения: " + getBirthDate() +  " Секрет: " + getSecretInformation() + " Я злой и опасный!" + "\n");
    }

    public void makeRoar () {
        System.out.println("Гррр, я злобный хищник");
    }
}
