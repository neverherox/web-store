package model.service.implementation;

import model.dao.implementation.CatalogDao;
import model.entity.Catalog;
import model.service.interfaces.CatalogService;

import java.util.List;

public class CatalogServiceImpl implements CatalogService {
    public List<Catalog> getCatalogs(){

        CatalogDao catalogDao = new CatalogDao();
        List<Catalog> catalogs = catalogDao.getAll();
        return catalogs;
    }

    public Catalog getCatalog(int id){

        CatalogDao catalogDao = new CatalogDao();
        Catalog catalog = catalogDao.getEntityById(id);
        return catalog;
    }
}
