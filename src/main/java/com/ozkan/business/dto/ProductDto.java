package com.ozkan.business.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private Long productId;

    @Size(min = 1, max = 20, message = "Product Name must between 1-20 characters!")
    private String productName;

    @Size(min = 1, max = 60, message = "Task Description must between 1-60 characters!")
    private String productDescription;

    private Integer productQuantity;

    private Double productPrice;
}
