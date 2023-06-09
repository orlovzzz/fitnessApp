package org.example.repo;

import org.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findAll();
    public Optional<Product> findByProduct(String product);
    public Optional<Product> findById(Long id);
}
