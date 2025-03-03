package com.bharath.catalog_service.domain;

import com.bharath.catalog_service.ApplicationProperties;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service //represents this is a service
@Transactional //this makes this service transactional since we deal with database, to maintain rollbacks if exceptions occur
public class ProductService {
    // injecting application properties pagesize config here
    private final ApplicationProperties properties;

    //dependency injection of product repository which uses spring data jpa
    private final ProductRepository productRepository;
    ProductService(ProductRepository productRepository, ApplicationProperties properties) {
        this.productRepository = productRepository;
        this.properties = properties;
    }

        //pagination to accept page no, by default its 1st page and accesses 10 records per page, specifying default
        //makes this non-mandatory parameter.

    // we will be using PagedResult record to return instead of simple list

    //instead of exposing ProductEntity directly to frontend we can use Product record, this product can then be mapped
    // to product entity, so that in controller we only expose product record instead of entity
    public PagedResult<Product> getProducts(int pageNo){

        // Pageable to access records with limit

        //when we do pagination its always best to sort it so the data retrieved is consistent
        Sort sort = Sort.by("name").ascending();

        //by default we get page numbers stating from 0 from spring jpa to make sure display pages from 1 in frontend
        pageNo = pageNo <= 1 ? 0 : pageNo - 1;

        Pageable pageable = PageRequest.of(pageNo, properties.pageSize(), sort);
        Page<Product> productsPage = productRepository.findAll(pageable) //when we do findAll it should return list
                .map(ProductMapper::toProduct); // mapping record to productEntity


        return new PagedResult<>(
                productsPage.getContent(),
                productsPage.getTotalElements(),
                productsPage.getNumber()+1, // when getNumber() is called it gives 0, so inc it by 1
                productsPage.getTotalPages(),
                productsPage.isFirst(),
                productsPage.isLast(),
                productsPage.hasNext(),
                productsPage.hasPrevious());
    }
}
