package com.restapi.restapi.Repo;

import com.restapi.restapi.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReposity extends JpaRepository<Product,Integer> {

}
