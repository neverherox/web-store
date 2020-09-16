package model.entity;

import java.io.Serializable;
import java.util.List;

public class Order extends Entity implements Serializable {
    private List<Product> products;
    private OrderStatus status;

    public Order() {

    }

    public Order(int id, List<Product> products, OrderStatus status) {
        this.id = id;
        this.products = products;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
