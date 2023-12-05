package com.miaoqi.amqp.service;

import com.miaoqi.amqp.bean.Book;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class BookService {

    @RabbitListener(queues = "miaoqi.news")
    // public void receive( Message message) {
    public void receive(Book book) {
        System.out.println("收到消息: " + book);
        // System.out.println("asmfp");
        // int i = 1 / 0;

        // try {
        //     channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        //     channel.basicRecover(true);
        // } catch (IOException e) {
        //     e.printStackTrace();
    }
}
