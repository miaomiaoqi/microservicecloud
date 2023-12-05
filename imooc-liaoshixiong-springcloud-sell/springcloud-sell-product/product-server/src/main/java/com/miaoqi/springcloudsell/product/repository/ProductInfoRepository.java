package com.miaoqi.springcloudsell.product.repository;

import com.miaoqi.springcloudsell.product.pojo.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品 dao
 *
 * @author miaoqi
 * @date 2019-06-07
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

    /**
     * 根据商品状态查询商品
     *
     * @author miaoqi
     * @date 2019-06-07
     * @param productStatus
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);

    List<ProductInfo> findByProductIdIn(List<String> productIdList);
}
