package com.miaoqi.springcloudsell.order.controller;

import com.miaoqi.springcloudsell.product.client.ProductClient;
import com.miaoqi.springcloudsell.product.common.DecreaseStockInput;
import com.miaoqi.springcloudsell.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class ClientController {

    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductList")
    public String getProductList() {
        List<ProductInfoOutput> productInfoList = this.productClient.listForOrder(Arrays.asList("164103465734242707"));
        log.error("response = [{}]", productInfoList);
        return "ok";
    }

    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock() {
        this.productClient.decreaseStock(Arrays.asList(new DecreaseStockInput("164103465734242707", 3)));
        return "ok";
    }


}
