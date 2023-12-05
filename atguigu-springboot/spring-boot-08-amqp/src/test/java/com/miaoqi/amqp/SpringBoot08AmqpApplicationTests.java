package com.miaoqi.amqp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.miaoqi.amqp.bean.Book;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot08AmqpApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Test
    public void createExchange() {
        //        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
        //        System.out.println("创建完成");

        //        amqpAdmin.declareQueue(new Queue("amqpadmin.queue", true));

        amqpAdmin.declareBinding(
                new Binding("amqpadmin.queue", DestinationType.QUEUE, "amqpadmin.exchange", "amqpadmin.haha", null));
    }

    /*
     * 1. 单播(点对点)
     */
    @Test
    public void test01() {
        // Message需要自己构造, 自定义消息内容和消息头
        //        rabbitTemplate.send(exchange, routingKey, message);

        // object默认当做消息体, 只要传入要发送的对象, 自动序列化
        //        rabbitTemplate.convertAndSend(exchange, routingKey, object);

        Map<String, Object> map = new HashMap<>();
        map.put("msg", "这是第一个消息");
        map.put("data", Arrays.asList("hello", 123, true));
        // 对象默认序列化以后发送出去
        rabbitTemplate.convertAndSend("exchange.direct", "miaoqi.news", new Book("西游记", "吴承恩"));
    }

    @Test
    public void receive() {
        Object obj = rabbitTemplate.receiveAndConvert("miaoqi.news");
        System.out.println(obj.getClass());
        System.out.println(obj);
    }

    @Test
    public void sendMsg() {
        rabbitTemplate.convertAndSend("exchange.fanout", "", new Book("红楼梦", "曹雪芹"));
    }

}
