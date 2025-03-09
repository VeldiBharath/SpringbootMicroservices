package com.bharath.catalog_service.domain;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    //this is sufficient for using spring data jpa, all methods will be in it
    //if needed any user defined methods can be written here

    //Internally JPA queries it by code instead of findByCode it uses select * from products where code = "";
    //this is Derived Query, we dont need to write the query JPA internally figures it out
    Optional<ProductEntity> findByCode(String code);
}
