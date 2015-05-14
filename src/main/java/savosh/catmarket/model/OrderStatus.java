package savosh.catmarket.model;

public enum OrderStatus {
    CREATED("C", "Created"),
    PROCESSED("P", "Precessed"),
    DELIVERED("D", "Delivered"),
    REFUSED("R", "Refused");

    String id;
    String name;

    private OrderStatus(String id) {
        this.id = id;
    }

    OrderStatus(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }
}
