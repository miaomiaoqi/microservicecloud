package com.miaoqi.springcloudsell.product.service.impl;

import com.miaoqi.springcloudsell.product.pojo.ProductCategory;
import com.miaoqi.springcloudsell.product.repository.ProductCategoryRepository;
import com.miaoqi.springcloudsell.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return this.productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }
}
