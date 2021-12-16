package com.javainuse.controller;


import com.javainuse.Transaction.InvalidRollbackInException;
import com.javainuse.Transaction.TransactionalService;
import com.javainuse.dao.EstatesRepository;
import com.javainuse.error.NotFoundException;
import com.javainuse.error.RoleBackException;
import com.javainuse.model.DAOEstate;
import com.javainuse.service.EstateService;
import com.javainuse.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by IntelliJ IDEA.
 * User: Alaa Alkheder
 * Email:alaa-alkheder@outlook.com
 * Github:alaa-alkheder
 */

@Component
//@Controller
@RestController
@RequestMapping(value = "/api/v1/estates")
public class EstatesController {

    @Autowired
    TransactionalService transactionalService;

    @Autowired
    private ParameterService parameterService;

    @Autowired
    private EstatesRepository estatesrepository;
    @Autowired
    private EstateService estateService;


    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<DAOEstate>> home() {
        List<DAOEstate> result = (List<DAOEstate>) estatesrepository.findAll();
        return new ResponseEntity<List<DAOEstate>>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DAOEstate> findById(@PathVariable int id) {
        try {

            DAOEstate result = estatesrepository.findById(id).get();
            return new ResponseEntity<DAOEstate>(result, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @GetMapping(value = {"/available"})
    // Todo add the condition to search
    public ResponseEntity<List<DAOEstate>> getAvailableHome() {
        List<DAOEstate> result = estatesrepository.findByStateIsFalse();
        return new ResponseEntity<List<DAOEstate>>(result, HttpStatus.OK);
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<DAOEstate> createNewEstate(@RequestBody DAOEstate estates) {
        DAOEstate result = estateService.createNewEstate(estates);
        return new ResponseEntity<DAOEstate>(result, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteEstate(@PathVariable int id) {
        try {
            estateService.deleteEstate(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @PutMapping(value = "/update/{id}/{buyer}")
    public DAOEstate updateEstate(@PathVariable int id, @PathVariable String buyer) {
        try {
            DAOEstate temp = estatesrepository.findById(Integer.valueOf(id)).get();
            temp.setBuyers(buyer);
            temp.setState(true);
            return estatesrepository.save(temp);
        } catch (NoSuchElementException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @PutMapping(value = "/update/{id}/{name}/{cost}/{shareCount}")
    public DAOEstate updateBasicElement(@PathVariable int id, @PathVariable String name, @PathVariable String cost, @PathVariable String shareCount) {
        try {
            DAOEstate temp = estatesrepository.findById(Integer.valueOf(id)).get();
            temp.setEstatesName(name);
            temp.setCost(Integer.parseInt(cost));
            temp.setNumberOfShares(Integer.parseInt(shareCount));
            return estatesrepository.save(temp);
        } catch (NoSuchElementException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    //    @Transactional(rollbackFor = InvalidRollbackInException.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @PostMapping("/sold/{estateId}")
    public String Sold(@PathVariable("estateId") String estateId, @RequestParam String SalerName, @RequestParam(defaultValue = "0") double price, HttpServletRequest request) throws InvalidRollbackInException {
        DAOEstate sold = estatesrepository.findById(Integer.valueOf(estateId)).get();
//        sold.setSoldAt(new Date());
        sold.setBuyers(SalerName);
//        if (price != 0)
//            sold.setCost((int) price);
        try {
//            transactionalService.canSave(estateId);
            sold.setState(true);
            estatesrepository.save(sold);
            ArrayList<DAOEstate> temp = (ArrayList<DAOEstate>) estatesrepository.findAll();
        } catch (
                ObjectOptimisticLockingFailureException e) {
            throw new RoleBackException(e.getMessage());
        }
        return "Done";
    }


}
