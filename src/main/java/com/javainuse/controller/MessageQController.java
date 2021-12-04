package com.javainuse.controller;


import com.javainuse.MessageQResource;
import com.javainuse.MessageQueueConfig;
import com.javainuse.dao.EstatesRepository;
import com.javainuse.model.DAOEstate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
public class MessageQController {

    @Autowired
    private RabbitTemplate template;
    
    @Autowired
    private EstatesRepository esatateReposotary;
    
    @PostMapping(path = "/divest/{email}/{content}/{month}")
    public String divest(@PathVariable String email,@PathVariable String content,@PathVariable String month) {
        MessageQResource message=new MessageQResource();
        message.setContent(content);
        message.setEmail(email);
        message.setMonth(Integer.parseInt(month));
        template.convertAndSend(MessageQueueConfig.topicExchangeName,
                                MessageQueueConfig.routingKey,
                                message);
//        ArrayList<DAOEstate> estates = (ArrayList<DAOEstate>) esatateReposotary.findAll();
//        request.setAttribute("estates", estates);
        return "done";
    }
    
/*
    @PostMapping(path = "/divest", consumes = "application/json")
    public void divest_F(@RequestBody MessageQResource message) {}
*/

    @GetMapping("/divest")
    public String go2StatisticsPage(HttpServletRequest request){
        return "statistics-page";
    }

}
