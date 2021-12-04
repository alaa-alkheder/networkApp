package com.testproject.demo.Controller;

 import com.testproject.demo.MessageQResource;
import com.testproject.demo.MessageQueueConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
 import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageQController {

    @RabbitListener(queues = MessageQueueConfig.queueName)
    public void Mailing(MessageQResource messageQ){
        System.out.println("We have a new Massage from :"+messageQ.getEmail()+"=> the Content Is: "+
                messageQ.getContent()+"/ in the month: "+messageQ.getMonth());

    }
}
