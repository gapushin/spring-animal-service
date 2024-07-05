package siebel.springanimal.animal.ranomizer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "animal")
public class RandomizerProperties {
    private List<String> names;
    private  List<String> breeds;
    private  List<Double> costs;
    private  List<String> characters;

}
