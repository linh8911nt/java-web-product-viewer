package service;

import java.sql.SQLException;
import model.Product;
import java.util.List;

public interface ProductService {
    List<Product> findAll() throws ClassNotFoundException, SQLException;
}
