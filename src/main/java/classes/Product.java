package classes;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Product implements BaseClass {
    private BaseClass parentObject;
    @Expose
    private List<Product> products;
    @Expose
    private String name;
    @Expose
    private BigDecimal price;

    public Product() {
        this.products = new ArrayList<>();
    }

    public Product(String name, BigDecimal price) {
        this();
        this.name = name;
        this.price = price;
    }

    public BaseClass getParentObject() {
        return parentObject;
    }

    public void setParentObject(BaseClass parentObject) {
        this.parentObject = parentObject;
    }

    @Override
    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public void addProduct(Product product) {
        if (product.getParentObject() != null && !product.getParentObject().equals(this)) {
            throw new IllegalArgumentException("The parent product must be null or current parent");
        }
        this.products.add(product);
        if (product.getParentObject() == null) {
            product.setParentObject(this);
        }
    }
}
