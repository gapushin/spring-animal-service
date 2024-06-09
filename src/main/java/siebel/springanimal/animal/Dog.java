package siebel.springanimal.animal;

import java.time.LocalDate;

public class Dog extends Pet {
    public Dog(
            String breed,
            String name,
            String character,
            Double cost,
            LocalDate birthDate
    ) {
        super(breed, name, character, cost, birthDate);
        this.className = "Dog";
    }

    @Override
    public void makePetSound() {
        System.out.println("Я собачка, тяф-тяф");
        super.makePetSound();
    }
}
