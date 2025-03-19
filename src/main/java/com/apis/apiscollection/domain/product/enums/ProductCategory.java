package com.apis.apiscollection.domain.product.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum ProductCategory {
    UNDEFINED, // default value
    ELECTRONICS,
    CLOTHING,
    FOOD,
    BOOKS,
    HOME,
    TOYS
}
