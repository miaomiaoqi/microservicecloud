package com.miaoqi.ticket.service;

import com.alibaba.dubbo.config.annotation.Service;

@Service // 将服务发布出去
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "厉害了, 我的国";
    }
}
