import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculateAverageCostByProducer {
    public static Map<String, Double> calculate(List<Sneakers> sneakersList) {
        Map<String, List<Double>> costByProducer = new HashMap<>();

        for (Sneakers sneaker : sneakersList) {
            String producerName = sneaker.producer().name();
            costByProducer.putIfAbsent(producerName, new ArrayList<>());
            costByProducer.get(producerName).add(sneaker.cost());
        }

        Map<String, Double> averageCost = new HashMap<>();
        for (Map.Entry<String, List<Double>> entry : costByProducer.entrySet()) {
            double totalCost = 0;
            for (double cost : entry.getValue()) {
                totalCost += cost;
            }
            averageCost.put(entry.getKey(), totalCost / entry.getValue().size());
        }

        return averageCost;
    }
}