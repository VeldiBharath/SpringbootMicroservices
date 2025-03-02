package com.bharath.catalog_service.domain;

import java.util.List;

// we are creating this to return a type of record which has all these properties
public record PagedResult<T>(
        List<T> data, //data in the page
        long totalElements, // length of page
        int pageNumber, //page number
        int totalPages, // total number of pages
        boolean isFirst, // is first page
        boolean isLast, // is last page
        boolean hasNext, // has next page
        boolean hasPrevious) //has previous page
        { }
