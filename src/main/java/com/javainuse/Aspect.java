package com.javainuse;

import com.javainuse.config.JwtRequestFilter;
import com.javainuse.dao.LogRepository;
import com.javainuse.model.DAOEstate;
import com.javainuse.model.DAOLog;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
//import sun.net.httpserver.AuthFilter;


/**
 * Created by IntelliJ IDEA.
 * User: Alaa Alkheder
 * Email:alaa-alkheder@outlook.com
 * Github:alaa-alkheder
 */


@Component
@org.aspectj.lang.annotation.Aspect
@EnableAspectJAutoProxy
public class Aspect {

    @Autowired
    private LogRepository logRepository;

    @AfterReturning(pointcut = "within(com.javainuse.controller.EstatesController)",returning = "estates")
    public void EstateAspect(DAOEstate estates){
        if(estates==null)
            return;
        String NewObj="estates{Id:"+estates.getId()+",Name:"+estates.getEstatesName()+",NumberOfShares:"+estates.getNumberOfShares()+
                ",cost:"+estates.getCost()+",Buyers:"+estates.getBuyers()+",Timestamp:"+estates.getTimestamp()+"}";
        addToLogs(estates,NewObj);

    }


    private void addToLogs(Object obj,String NewData){
        DAOLog log=new DAOLog();
        log.setObj(NewData);
        //todo add userName to aspect Function
//        log.setName(JwtRequestFilter.userDetails.getUsername());
        log.setName("soso.so");
        logRepository.save(log);
    }
}
