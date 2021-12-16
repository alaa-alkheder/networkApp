package com.javainuse.service;

import com.javainuse.dao.EstatesRepository;
import com.javainuse.error.NotFoundException;
import com.javainuse.model.DAOEstate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.NoSuchElementException;

/**
 * Created by IntelliJ IDEA.
 * User: Alaa Alkheder
 * Email:alaa-alkheder@outlook.com
 * Github:alaa-alkheder
 */
@Service
public class EstateService {
    @Autowired
    private EstatesRepository estatesRepository;
    @Autowired
    private ParameterService parameterService;


    public DAOEstate findById(@PathVariable int id) {
        try {
            return estatesRepository.findById(id).get();

        } catch (NoSuchElementException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    public DAOEstate createNewEstate(DAOEstate estates) {
        if (estates.getNumberOfShares() == -1)
            estates.setNumberOfShares(Integer.parseInt(parameterService.findById(1).getValue()));
        estates.setCost(estates.getCost() * Integer.parseInt(parameterService.findById(2).getValue()));
        return estatesRepository.save(estates);
    }

    public void deleteEstate(int id) {

        estatesRepository.deleteById(id);

    }

    public DAOEstate updateEstate(int id, String buyer) {
        try {
            DAOEstate temp = estatesRepository.findById(Integer.valueOf(id)).get();
            temp.setBuyers(buyer);
            temp.setState(true);
            return estatesRepository.save(temp);
        } catch (NoSuchElementException e) {
            throw new NotFoundException(e.getMessage());
        }
    }
}
