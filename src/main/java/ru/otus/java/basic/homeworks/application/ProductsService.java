package ru.otus.java.basic.homeworks.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.otus.java.basic.homeworks.processors.DeleteProductProcessor;

import java.util.*;

import static org.apache.logging.log4j.ThreadContext.removeAll;

public class ProductsService {
    private List<Product> products;
    private static final Logger LOGGER = LogManager.getLogger(ProductsService.class);

    public ProductsService() {
        this.products = new ArrayList<>(Arrays.asList(
                new Product(1L, "Milk"),
                new Product(2L, "Bread"),
                new Product(3L, "Cheese")
        ));
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

    public Product getProductById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().get();
    }

    public void createNewProduct(Product product) {
        Long newId = products.stream().mapToLong(Product::getId).max().getAsLong() + 1;
        products.add(new Product(newId, product.getTitle()));
    }

    public void deleteAllProducts() {
        products.removeAll(this.products);
        LOGGER.info("Удаляем всё");
    }

    public void deleteProductById(Long id) {
        products.remove(products.stream().filter(p -> p.getId().equals(id)).findFirst().get());
        LOGGER.info("Удалили с id=" + id);
    }
    public void updateProductById(Long id, String title) {
        products.stream().filter(p -> p.getId().equals(id)).findFirst().get().setTitle(title);
        LOGGER.info("Изменили с id=" + id);
    }

}
