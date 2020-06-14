package dao.interfaces;

import entity.Product;

import java.util.ArrayList;

public interface IProductDao {
    ArrayList<Product> GetProducts();
    Product GetProduct(int id);
    void EditProduct(Product product);
    void DeleteProduct(Product product);
    void AddProduct(Product product);
}
