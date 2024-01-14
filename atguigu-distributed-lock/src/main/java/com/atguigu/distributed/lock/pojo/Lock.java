package com.atguigu.distributed.lock.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_lock")
public class Lock {

    private Long id;
    private String lockName;

}
