package com.javainuse.service;

import com.javainuse.dao.ParameterRepository;
import com.javainuse.model.DAOParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alaa Alkheder
 * Email:alaa-alkheder@outlook.com
 * Github:alaa-alkheder
 */
@Service
public class ParameterService {
    @Autowired
    private ParameterRepository parameterService;

    @CacheEvict("params")
    public List<DAOParameter> findAll() {
        return (List<DAOParameter>) parameterService.findAll();
    }
    @CacheEvict("params")
    public DAOParameter findById(int id) {
        return parameterService.findById(id).get();
    }


    @CacheEvict("params")
    public DAOParameter save(String key, String value) {
        return parameterService.save(new DAOParameter(key, value));

    }

    @CacheEvict("params")
    public DAOParameter updateParameter(String id, String key, String value) {
        //todo write body of function
        DAOParameter daoParameter = parameterService.findById(Integer.valueOf(id)).get();
        daoParameter.setKey(key);
        daoParameter.setValue(value);
        return parameterService.save(daoParameter);

    }
}