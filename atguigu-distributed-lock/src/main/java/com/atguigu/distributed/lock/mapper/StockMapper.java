package com.atguigu.distributed.lock.mapper;

import com.atguigu.distributed.lock.pojo.Stock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StockMapper extends BaseMapper<Stock> {

    @Select("select * from tb_stock where product_code = #{productCode} for update")
    List<Stock> queryStock(String productCode);

}
