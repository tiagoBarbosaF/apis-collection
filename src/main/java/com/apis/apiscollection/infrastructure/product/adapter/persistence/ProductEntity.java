package com.apis.apiscollection.infrastructure.product.adapter.persistence;

import com.apis.apiscollection.domain.product.enums.ProductCategory;
import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "product")
class ProductEntity {
    @Id
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    private String sku;
    @Column(name = "stock_quantity")
    private int stockQuantity;
    @Enumerated(EnumType.STRING)
    private ProductCategory category;
    private String brand;
    private double weight;
    private double height;
    private double width;
    private double length;
    @Column(name = "image_url")
    private String imageUrl;
    private boolean active;

    protected ProductEntity() {
    }

    private ProductEntity(Builder builder) {
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

    @PrePersist
    protected void prePersist() {
        if (id == null) id = UuidCreator.getTimeOrderedEpoch();
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
            price = val == null ? BigDecimal.ZERO : val;
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
            category = val == null ? ProductCategory.UNDEFINED : val;
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

        public ProductEntity build() {
            return new ProductEntity(this);
        }
    }
}
