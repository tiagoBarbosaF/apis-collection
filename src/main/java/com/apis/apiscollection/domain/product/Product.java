package com.apis.apiscollection.domain.product;

import com.apis.apiscollection.domain.product.enums.ProductCategory;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
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

    protected Product() {}

    private Product(Builder builder) {
        id = builder.id;
        name = builder.name;
        description = builder.description;
        price = builder.price;
        sku = builder.sku;
        stockQuantity = builder.stockQuantity;
        category = builder.category;
        brand = builder.brand;
        weight = builder.weight;
        height = builder.height;
        width = builder.width;
        length = builder.length;
        imageUrl = builder.imageUrl;
        active = builder.active;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSku() {
        return sku;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isActive() {
        return active;
    }

    public Product updateProduct(Product product) {
        return Product.builder()
                .id(this.id)
                .name(product.getName() == null ? this.name : product.getName())
                .description(product.getDescription() == null ? this.description : product.getDescription())
                .price(product.price == null ? this.price : product.getPrice())
                .sku(product.getSku() == null ? this.sku : product.getSku())
                .stockQuantity(product.getStockQuantity())
                .category(product.getCategory())
                .brand(product.getBrand() == null ? this.brand : product.getBrand())
                .weight(product.getWeight())
                .height(product.getHeight())
                .width(product.getWidth())
                .length(product.getLength())
                .imageUrl(product.getImageUrl() == null ? this.imageUrl : product.getImageUrl())
                .active(product.isActive())
                .build();
    }

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

        public Product build() {
            return new Product(this);
        }
    }
}
