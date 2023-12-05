package com.miaoqi.springcloudsell.product.service;

import com.miaoqi.springcloudsell.product.pojo.ProductCategory;

import java.util.List;

/**
 * 类目业务类
 *
 * @author miaoqi
 * @date 2019-06-07
 */
public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
