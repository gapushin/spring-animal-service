package siebel.springanimal.animal;

import java.time.LocalDate;

public class Shark extends Predator {
    public Shark(
            String breed,
            String name,
            String character,
            Double cost,
            LocalDate birthDate
    ) {
        super(breed, name, character, cost, birthDate);
        this.className = "Shark";
    }

    @Override
    public void makeRoar () {
        System.out.println("Клац, клац! Я Акулка!");
    }
}
