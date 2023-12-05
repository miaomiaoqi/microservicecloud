package com.miaoqi.springcloudsell.product.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 商品上下架状态枚举
 *
 * @author miaoqi
 * @date 2019-06-07
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ProductStatusEnum {

    /**
     *
     */
    UP(0, "上架"),
    DOWN(1, "下架"),
    ;

    private Integer code;

    private String desc;

}
