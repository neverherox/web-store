package model.service.implementation;

import model.dao.implementation.ProductDao;
import model.entity.Product;
import model.service.interfaces.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    public void deleteProduct(Product product) {
        ProductDao productDao = new ProductDao();
        productDao.deleteEntity(product);
    }

    public void addProduct(Product product) {
        ProductDao productDao = new ProductDao();
        productDao.addEntity(product);
    }
    public void editProduct(Product product){
        ProductDao productDao = new ProductDao();
        productDao.editEntity(product);
    }
    public List<Product> getProducts(){
        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.getAll();
        return products;
    }
    public Product getProduct(int id){

        ProductDao productDao = new ProductDao();
        Product product = productDao.getEntityById(id);
        return product;
    }

}
