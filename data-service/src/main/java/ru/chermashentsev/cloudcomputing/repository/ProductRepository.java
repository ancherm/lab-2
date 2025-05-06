package ru.chermashentsev.cloudcomputing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chermashentsev.cloudcomputing.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
