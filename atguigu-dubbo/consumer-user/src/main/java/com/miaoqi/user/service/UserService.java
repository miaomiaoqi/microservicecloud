package com.miaoqi.user.service;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.miaoqi.ticket.service.TicketService;

@Service
public class UserService {

    @Reference
    private TicketService ticketService;

    public void hello() {
        String ticket = this.ticketService.getTicket();
        System.out.println("买到票了: " + ticket);
    }

}
