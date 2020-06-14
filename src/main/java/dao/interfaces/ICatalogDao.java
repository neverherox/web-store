package dao.interfaces;

import entity.Catalog;

import java.util.ArrayList;

public interface ICatalogDao {

    ArrayList<Catalog> GetCatalogs();
    Catalog GetCatalog(int id);
}
