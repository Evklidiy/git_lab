import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculateAverageCostByType {
    public static Map<String, Double> calculate(List<Sneakers> sneakersList) {
        Map<String, List<Double>> costByType = new HashMap<>();

        for (Sneakers sneaker : sneakersList) {
            String type = sneaker.type();
            costByType.putIfAbsent(type, new ArrayList<>());
            costByType.get(type).add(sneaker.cost());
        }

        Map<String, Double> averageCost = new HashMap<>();
        for (Map.Entry<String, List<Double>> entry : costByType.entrySet()) {
            double totalCost = 0;
            for (double cost : entry.getValue()) {
                totalCost += cost;
            }
            averageCost.put(entry.getKey(), totalCost / entry.getValue().size());
        }

        return averageCost;
    }
}