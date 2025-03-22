package com.apis.apiscollection.application.product.dto;

import com.apis.apiscollection.domain.product.enums.ProductCategory;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        String sku,
        int stockQuantity,
        ProductCategory category,
        String brand,
        double weight,
        double height,
        double width,
        double length,
        String imageUrl,
        boolean active
) {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID id;
        private String name;
        private String description;
        private BigDecimal price;
        private String sku;
        private int stockQuantity;
        private ProductCategory category;
        private String brand;
        private double weight;
        private double height;
        private double width;
        private double length;
        private String imageUrl;
        private boolean active;

        public Builder() {
        }

        public Builder id(UUID val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder price(BigDecimal val) {
            price = val;
            return this;
        }

        public Builder sku(String val) {
            sku = val;
            return this;
        }

        public Builder stockQuantity(int val) {
            stockQuantity = val;
            return this;
        }

        public Builder category(ProductCategory val) {
            category = val;
            return this;
        }

        public Builder brand(String val) {
            brand = val;
            return this;
        }

        public Builder weight(double val) {
            weight = val;
            return this;
        }

        public Builder height(double val) {
            height = val;
            return this;
        }

        public Builder width(double val) {
            width = val;
            return this;
        }

        public Builder length(double val) {
            length = val;
            return this;
        }

        public Builder imageUrl(String val) {
            imageUrl = val;
            return this;
        }

        public Builder active(boolean val) {
            active = val;
            return this;
        }

        public ProductResponse build() {
            return new ProductResponse(
                    this.id,
                    this.name,
                    this.description,
                    this.price,
                    this.sku,
                    this.stockQuantity,
                    this.category,
                    this.brand,
                    this.weight,
                    this.height,
                    this.width,
                    this.length,
                    this.imageUrl,
                    this.active
            );
        }
    }
}
