package com.miaoqi.springcloudsell.product.repository;

import com.miaoqi.springcloudsell.product.pojo.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品分类 dao
 *
 * @author miaoqi
 * @date 2019-06-07
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
