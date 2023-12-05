package com.miaoqi.springcloud.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 drop database if exists cloudDB03;
 create database cloudDB03 character set utf8;
 use cloudDB03;
 create table dept(
 deptno bigint not null primary key auto_increment,
 dname varchar(60),
 db_source varchar(60)
 );
 insert into dept(dname, db_source) values('开发部', DATABASE());
 insert into dept(dname, db_source) values('人事部', DATABASE());
 insert into dept(dname, db_source) values('财务部', DATABASE());
 insert into dept(dname, db_source) values('市场部', DATABASE());
 insert into dept(dname, db_source) values('运维部', DATABASE());
 */

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Dept implements Serializable// entity --orm--- db_table
{
    private Long deptno; // 主键
    private String dname; // 部门名称
    private String db_source;// 来自那个数据库，因为微服务架构可以一个服务对应一个数据库，同一个信息被存储到不同数据库

    public Dept(String dname) {
        this.dname = dname;
    }

    @Override
    public String toString() {
        return "Dept [deptno=" + deptno + ", dname=" + dname + ", db_source=" + db_source + "]";
    }

}