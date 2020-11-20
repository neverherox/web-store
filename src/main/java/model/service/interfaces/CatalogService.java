package model.service.interfaces;

import model.entity.Catalog;

import java.util.List;

public interface CatalogService {
    List<Catalog> getCatalogs();

    Catalog getCatalog(int id);
}
