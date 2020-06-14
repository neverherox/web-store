package entity;

import java.io.Serializable;

public class Product implements Serializable{
    private int id;
    private String name;
    private String description;
    private double price;
    private int catalogId;
    private String image;
    public Product()
    {

    }
    public Product(int id, String name, String description, double price, int catalogId, String image)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.catalogId = catalogId;
        this.image = image;
    }
    public Product(int catalogId,String name, double price, String description)
    {
        this.catalogId = catalogId;
        this.name = name;
        this.price = price;
        this.description = description;
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
        this.name =name;
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

    public int getCatalogId() {
        return catalogId;
    }
    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

}
