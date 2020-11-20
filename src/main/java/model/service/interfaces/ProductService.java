package model.service.interfaces;

import model.entity.Product;

import java.util.List;

public interface ProductService {
    void deleteProduct(Product product);

    void addProduct(Product product);

    void editProduct(Product product);

    List<Product> getProducts();

    Product getProduct(int id);

}
