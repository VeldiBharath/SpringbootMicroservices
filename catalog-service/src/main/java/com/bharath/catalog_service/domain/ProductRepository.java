package com.bharath.catalog_service.domain;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    //this is sufficient for using spring data jpa, all methods will be in it
    //if needed any user defined methods can be written here

}
