package com.javainuse.controller;


import com.javainuse.dao.ParameterRepository;
import com.javainuse.model.DAOParameter;
import com.javainuse.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alaa Alkheder
 * Email:alaa-alkheder@outlook.com
 * Github:alaa-alkheder
 */
@RestController
@CrossOrigin
public class ParameterController {
    @Autowired
    private ParameterService parameterService;

    @Value("${server.port}")
    private int port;

    @GetMapping(value = "/")
    public int  testLoadBalens(){
        return port;
    }

    @GetMapping(value = "/parameter")
    public List<DAOParameter> home() {
        return parameterService.findAll();
    }


    @GetMapping(value = {"/parameter/{id}"})
    public DAOParameter getById(@PathVariable int id) {
        return parameterService.findById(id);
    }


    @RequestMapping(value = "/parameter/{key}/{value}", method = RequestMethod.POST)
    public DAOParameter createNewParameter(@PathVariable String key, @PathVariable String value) {

        DAOParameter result = parameterService.save(key, value);
        return result;
//        return new ResponseEntity<DAOParameter>(result, HttpStatus.CREATED);
    }


    @PostMapping(value = "/parameter/update/{id}/{value}")
    public DAOParameter updateParameter(@PathVariable String id, @PathVariable String value) {
        return parameterService.updateParameter(id,  value);

    }

}
