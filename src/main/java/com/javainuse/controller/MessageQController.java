package com.javainuse.controller;



import com.javainuse.MessageQResource;
import com.javainuse.MessageQueueConfig;
import com.javainuse.dao.EstatesRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return "done";
    }



}
