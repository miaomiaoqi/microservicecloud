package com.miaoqi.springcloudsell.product.controller;

import com.miaoqi.springcloudsell.product.common.DecreaseStockInput;
import com.miaoqi.springcloudsell.product.common.ProductInfoOutput;
import com.miaoqi.springcloudsell.product.pojo.ProductCategory;
import com.miaoqi.springcloudsell.product.pojo.ProductInfo;
import com.miaoqi.springcloudsell.product.service.CategoryService;
import com.miaoqi.springcloudsell.product.service.ProductService;
import com.miaoqi.springcloudsell.product.utils.ResultVOUtil;
import com.miaoqi.springcloudsell.product.vo.ProductInfoVO;
import com.miaoqi.springcloudsell.product.vo.ProductVO;
import com.miaoqi.springcloudsell.product.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品 controller
 *
 * @author miaoqi
 * @date 2019-06-07
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 查询所有在架商品
        List<ProductInfo> productInfoList = this.productService.findUpAll();
        // 获取类目 type 列表
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        // 从数据库查询类目
        List<ProductCategory> categoryList = this.categoryService.findByCategoryTypeIn(categoryTypeList);

        // 构造数据
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : categoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }

    /**
     * 获取商品列表(给订单服务使用)
     *
     * @author miaoqi
     * @date 2019-06-09
     * @param productIdList
     * @return
     */
    @PostMapping("/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.productService.findList(productIdList);
    }

    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> cartDTOList) {
        this.productService.decreaseStock(cartDTOList);
    }

}
