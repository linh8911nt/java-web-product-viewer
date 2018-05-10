package service;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    static final String DB_URL = "jdbc:mysql://localhost:3306/productviewer" + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "12345678";

    public ProductServiceImpl() {
    }

    @Override
    public List<Product> findAll() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        Connection connection = null;
        Statement statement = null;

        log("Dang ket noi toi co so du lieu ...");
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        log("Tao lenh truy van SQL ...");
        statement = connection.createStatement();

        String sql;
        sql = "SELECT id, code, name FROM products ORDER BY code ASC";

        log("Dang thuc hien truy van...");
        ResultSet resultSet = statement.executeQuery(sql);

        List<Product> products = new ArrayList<>();
        log("Dang thu thap ket qua...");
        while (resultSet.next()){
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setCode(resultSet.getString("code"));
            product.setName(resultSet.getString("name"));

            products.add(product);
        }

        log("Hoan thanh thu thap ket qua. Dang dong cac ket noi...");
        resultSet.close();
        statement.close();
        connection.close();

        log("Thanh cong!");
        return products;
    }

    private void log(String message) {
        System.out.println("ProductServiceImpl: " + message);
    }
}
