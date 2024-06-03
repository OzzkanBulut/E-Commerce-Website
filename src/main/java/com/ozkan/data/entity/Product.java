package com.ozkan.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name", length = 20, nullable = false, columnDefinition = "varchar(255) default USER")
    @NotEmpty(message = "Task Name cannot be null!")
    private String productName;

    private String productImage;

    @Column(name = "product_desc",length = 60,nullable = false,columnDefinition = "varchar(255) default USER")
    private String productDescription;

    @Column(name = "product_quantity")
    private Integer productQuantity;

    @Column(name = "product_price")
    private Double productPrice;





}
