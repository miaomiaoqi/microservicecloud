package com.miaoqi.springcloudsell.order.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miaoqi.springcloudsell.order.dto.OrderDTO;
import com.miaoqi.springcloudsell.order.enums.ResultEnum;
import com.miaoqi.springcloudsell.order.exception.OrderException;
import com.miaoqi.springcloudsell.order.form.OrderForm;
import com.miaoqi.springcloudsell.order.pojo.OrderDetail;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 廖师兄
 * 2017-12-10 17:38
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("【json转换】错误, string={}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
