package model.entity;

import java.io.Serializable;

public class Catalog extends Entity implements Serializable{
    private String name;

    public Catalog() {

    }
    public Catalog(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
