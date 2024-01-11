package com.atguigu.distributed.lock.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("tb_stock")
@Data
public class Stock {

    private Integer id;

    private String productCode;

    private String warehouse;

    private Integer count;

    private Integer version;

}
