package com.eric.cakemall.repository;

import com.eric.cakemall.model.Product;
import com.eric.cakemall.model.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImgRepository extends JpaRepository<ProductImg, Integer> {
    List<ProductImg> findByProduct(Product product);
}
