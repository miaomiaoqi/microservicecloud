package com.miaoqi.springcloudsell.product.service;

import com.miaoqi.springcloudsell.product.common.DecreaseStockInput;
import com.miaoqi.springcloudsell.product.common.ProductInfoOutput;
import com.miaoqi.springcloudsell.product.pojo.ProductInfo;

import java.util.List;

public interface ProductService {

    /**
     * 查询所有上架商品
     *
     * @author miaoqi
     * @date 2019-06-07
     * @param
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     *
     * @author miaoqi
     * @date 2019-06-09
     * @param productIdList
     * @return
     */
    List<ProductInfoOutput> findList(List<String> productIdList);

    /**
     * 扣库存
     *
     * @author miaoqi
     * @date 2019-06-09
     * @param decreaseStockInputList
     * @return
     */
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
