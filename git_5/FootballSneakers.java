public class FootballSneakers extends Sneakers {
    public FootballSneakers(String name, double cost, Producer producer) {
        super(name, cost, producer);
    }

    @Override
    public String type() {
        return "Football";
    }
}