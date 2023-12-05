package com.miaoqi.springcloudsell.product.dto;

import lombok.Data;

@Data
public class CartDTO {
    /**
     * 商品 id
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;
}
