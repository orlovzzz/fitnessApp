package org.example.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    List<Product> products;

    @Column(name = "customer_name")
    private String customer_name;

    @Column(name = "ready")
    private boolean ready = false;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getCustomerName() {
        return customer_name;
    }

    public void setCustomerName(String customer_name) {
        this.customer_name = customer_name;
    }

    public void addToProducts(Product product) {
        products.add(product);
    }

    public boolean isReady() {
        return ready;
    }

    public Long getId() {
        return id;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }
}
