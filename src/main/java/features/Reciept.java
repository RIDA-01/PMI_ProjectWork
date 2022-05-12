package features;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Reciept {
    private Integer id;
    private String costumerName;
    private String meal;
    private Double Price;
    private String date;

    public Reciept() {
        this.id = 1;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd_HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.date = dtf.format(now);
    }

    public Reciept(Integer id, String costumerName, String meal,  Double discount, Double Price, String date) {
        this.id = id;
        this.costumerName = costumerName;
        this.meal = meal;
        this.Price = Price;
        this.date = date;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCostumerName() {
        return costumerName;
    }

    public void setCostumerName(String costumerName) {
        this.costumerName = costumerName;
    }

    public String getmeal() {
        return meal;
    }

    public void setMealName(String meal) {
        this.meal= meal;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void add(Reciept reciept) {
    }
}
