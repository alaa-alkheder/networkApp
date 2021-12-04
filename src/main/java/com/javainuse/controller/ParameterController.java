package com.javainuse.controller;


import com.javainuse.dao.ParameterRepository;
import com.javainuse.model.DAOParameter;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ParameterRepository parameterService;

    @Cacheable("params")
    @GetMapping(value = {"", "/"})
    public List<DAOParameter> home() {
        return (List<DAOParameter>) parameterService.findAll();
    }

    @Cacheable("params")
    @GetMapping(value = {"/{id}"})
    public DAOParameter getById(@PathVariable int id) {
        return parameterService.findById(id).get();
    }

    @Cacheable("params")
    @RequestMapping(value = "/parameter/{key}/{value}", method = RequestMethod.POST)
    public ResponseEntity<DAOParameter> createNewEstate(@PathVariable String key,@PathVariable String value) {

        DAOParameter result = parameterService.save(new DAOParameter(key, value));
        return new ResponseEntity<DAOParameter>(result, HttpStatus.CREATED);
    }

    @CacheEvict("params")
    @PutMapping(value = "/update/{id}")
    public DAOParameter updateEstate(@PathVariable String id, @RequestBody DAOParameter parameter) {
//        return parameterService.update(id, parameter);
    return null;
    }

}
