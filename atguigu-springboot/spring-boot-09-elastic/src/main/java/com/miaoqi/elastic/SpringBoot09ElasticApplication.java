package com.miaoqi.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * SpringBoot默认支持两种技术和ES交互
 * 1. Jest(默认不生效)
 *  需要导入Jest的工具包(io.searchbox.client.JestClient)
 * 2. SpringDataElasticSearch[ES版本有可能不合适]
 *      1). Client clusterNodes, clusterName
 *      2). ElasticsearchTemplate操作es
 *      3). 编写ElasticsearchRepository的子接口来操作es
 */
/*
 *   PUT:增改  DELETE:删    GET:查
 *   http://127.0.0.1:9200/索引/类型/id     ===>    http://127.0.0.1:9200/数据库/表/记录  ES的属性等同于Solr的Field
 *   http://127.0.0.1:9200/megacorp/employee/1
 *   http://127.0.0.1:9200/megacorp/employee/_search
 *   http://127.0.0.1:9200/megacorp/employee/_search?q=last_name:Smith
 *   还支持查询表达式, 复杂查询表达式
 */
@SpringBootApplication
public class SpringBoot09ElasticApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot09ElasticApplication.class, args);
    }
}
