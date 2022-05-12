package features;

public class Meal {
    private String name;
    private String shopkeeper;
    private Double price;

    public Meal() {
        this.name = "default";
        this.shopkeeper = "default";
        this.price = 0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopkeeper() {
        return shopkeeper;
    }

    public void setShopkeeper(String shopkeeper) {
        this.shopkeeper = shopkeeper;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Meal(String name, String shopkeeper, Double price) {
        this.name = name;
        this.shopkeeper = shopkeeper;
        this.price = price;
    }
}
