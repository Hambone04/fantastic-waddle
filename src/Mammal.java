import java.util.ArrayList;
import java.util.Arrays;

public class Mammal extends Fauna {
    public Mammal(String info) {
        ArrayList<String> splitInfo = new ArrayList<String>(Arrays.asList(info.split(",")));

        species = splitInfo.get(2);
        scientific = splitInfo.get(3);
        continent = splitInfo.get(4);
        fact = splitInfo.get(5);
    }   
}