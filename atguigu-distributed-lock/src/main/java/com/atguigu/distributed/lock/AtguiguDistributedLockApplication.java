package com.atguigu.distributed.lock;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.atguigu.distributed.lock.mapper")
public class AtguiguDistributedLockApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtguiguDistributedLockApplication.class, args);
	}

}
