package ru.otus.java.basic.homeworks.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseProcessor {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final Logger LOGGER = LogManager.getLogger(DatabaseProcessor.class);
    private Connection connection;

    private static final String GET_ALL_PRODUCTS_QUERY = "select p.id, p.title from public.products p";
    private static final String GET_PRODUCT_QUERY = "select p.title from public.products p where p.id = ?";
    private static final String DELETE_ALL_PRODUCTS_QUERY = "delete from public.products p";
    private static final String DELETE_PRODUCT_QUERY = "delete from public.products p where p.id = ?";
    private static final String INSERT_PRODUCT_QUERY = "insert into public.products (id, title) values (?, ?)";
    private static final String UPDATE_PRODUCT_QUERY = "update public.products set title = ? where id = ?";

    public void initialize() {
        try {
            this.connection = DriverManager.getConnection(DATABASE_URL, "postgres", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        LOGGER.info("Инициализация DatabaseProcessor");
    }

    public List<Product> getAllProducts() {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(GET_ALL_PRODUCTS_QUERY);
            List<Product> products = new ArrayList<>();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    products.add(new Product(resultSet.getLong(1), resultSet.getString(2)));
                }
                LOGGER.info("Находим все продукты!");
                return Collections.unmodifiableList(products);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Product getProductById(Long id) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(GET_PRODUCT_QUERY);
            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    if (resultSet.getString(1) != null) {
                        LOGGER.info("Находим продукт по идентификатору!");
                        return new Product(id, resultSet.getString(1));
                    } else {
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }

    public void deleteAllProducts() {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(DELETE_ALL_PRODUCTS_QUERY);
            preparedStatement.execute();
            LOGGER.info("Удалили все продукты!");
        } catch (SQLException e) {
            LOGGER.info("Исключение при удалении всех продуктов!" + e);
            e.printStackTrace();
        }
    }

    public void deleteProductById(Long id) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(DELETE_PRODUCT_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            LOGGER.info("Удалили продукт по идентификатору!");
        } catch (SQLException e) {
            LOGGER.info("Исключение при удалении продукта по идентификатору!" + e);
            e.printStackTrace();
        }
    }

    public void updateProductById(Long id, String title) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(UPDATE_PRODUCT_QUERY);
            preparedStatement.setString(1, title);
            preparedStatement.setLong(2, id);
            preparedStatement.execute();
            LOGGER.info("Изменен продукт по идентификатору!");
        } catch (SQLException e) {
            LOGGER.info("Исключение при изменении продукта по идентификатору!" + e);
            e.printStackTrace();
        }
    }

    public void createNewProduct(Long id, String title) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(INSERT_PRODUCT_QUERY);
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, title);
            preparedStatement.execute();
            LOGGER.info("Добавлен продукт!");
        } catch (SQLException e) {
            LOGGER.info("Исключение при добавлении продукта по идентификатору!" + e);
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
