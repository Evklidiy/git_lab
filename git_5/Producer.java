public class Producer {
    private final String name;
    private final String country;

    public Producer(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String name() {
        return name;
    }

    public String country() {
        return country;
    }
}