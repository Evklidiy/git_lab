public class RunningSneakers extends Sneakers {
    public RunningSneakers(String name, double cost, Producer producer) {
        super(name, cost, producer);
    }

    @Override
    public String type() {
        return "Running";
    }
}