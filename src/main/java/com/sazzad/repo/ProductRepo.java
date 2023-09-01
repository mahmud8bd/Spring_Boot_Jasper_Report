package com.sazzad.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.sazzad.model.Product;

@Service
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
