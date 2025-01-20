import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountUniqueProducers {
    public static int count(List<Sneakers> sneakersList) {
        Map<String, Boolean> producers = new HashMap<>();
        for (Sneakers sneaker : sneakersList) {
            producers.put(sneaker.producer().name(), true);
        }
        return producers.size();
    }
}