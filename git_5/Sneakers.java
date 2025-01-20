public abstract class Sneakers {
    private final String name;
    private final double cost;
    private final Producer producer;

    public Sneakers(String name, double cost, Producer producer) {
        this.name = name;
        this.cost = cost;
        this.producer = producer;
    }

    public String name() {
        return name;
    }

    public double cost() {
        return cost;
    }

    public Producer producer() {
        return producer;
    }

    public abstract String type();
}