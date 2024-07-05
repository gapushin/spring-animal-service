package siebel.springanimal.animal;

import java.time.LocalDate;

public class Wolf extends Predator {
    public Wolf (
            String breed,
            String name,
            String character,
            Double cost,
            LocalDate birthDate
    ) {
        super(breed, name, character, cost, birthDate);
        this.className = "Wolf";
    }

    @Override
    public void makeRoar () {
        System.out.println("Я волк - я вою на луну: ауууууууу");
    }
}
