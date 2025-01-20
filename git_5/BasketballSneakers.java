public class BasketballSneakers extends Sneakers {
    public BasketballSneakers(String name, double cost, Producer producer) {
        super(name, cost, producer);
    }

    @Override
    public String type() {
        return "Basketball";
    }
}