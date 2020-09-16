package model.entity;

import java.io.Serializable;
import java.util.List;

public class Product extends Entity implements Serializable {
    private String name;
    private String description;
    private double price;
    private Catalog catalog;
    private String image;

    public Product() {

    }


    public Product(int id, String name, String description, double price, Catalog catalog, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.catalog = catalog;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
