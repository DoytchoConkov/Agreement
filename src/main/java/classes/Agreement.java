package classes;

import com.google.gson.annotations.Expose;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Agreement implements BaseClass {
    @Expose
    private String name;
    @Expose
    private String signedBy;
    @Expose
    private List<Product> products;

    public Agreement() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.name = "Agreement " + LocalDate.now().format(formatter);
        this.products = new ArrayList<>();
    }

    public Agreement(String signedBy) {
        this();
        this.signedBy = signedBy;
    }

    public String getName() {
        return name;
    }

    public String getSignedBy() {
        return signedBy;
    }

    public void setSignedBy(String signedBy) {
        this.signedBy = signedBy;
    }

    @Override
    public List<Product> getProducts() {
        return products;
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
