package com.javainuse.controller;


import com.javainuse.Transaction.InvalidRollbackInException;
import com.javainuse.Transaction.TransactionalService;
import com.javainuse.dao.EstatesRepository;
import com.javainuse.error.NotFoundException;
import com.javainuse.error.RoleBackException;
import com.javainuse.model.DAOEstate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
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
    private EstatesRepository estatesService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<DAOEstate>> home() {
        List<DAOEstate> result = (List<DAOEstate>) estatesService.findAll();
        return new ResponseEntity<List<DAOEstate>>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DAOEstate> findById(@PathVariable int id) {
        try {

            DAOEstate result = estatesService.findById(id).get();
            return new ResponseEntity<DAOEstate>(result, HttpStatus.OK);
        }catch (NoSuchElementException e){
            throw new NotFoundException(e.getMessage());
        }
    }

    @GetMapping(value = {"/available"})
    // Todo add the condition to search
    public ResponseEntity<List<DAOEstate>> getAvailableHome() {
        List<DAOEstate> result = estatesService.findByStateIsFalse();
        return new ResponseEntity<List<DAOEstate>>(result, HttpStatus.OK);
    }

    @PostMapping(value = {"", "/"})
    public ResponseEntity<DAOEstate> createNewEstate(@RequestBody DAOEstate estates) {
        DAOEstate result = estatesService.save(estates);
        return new ResponseEntity<DAOEstate>(result, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteEstate(@PathVariable int id) {
        try {
        estatesService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }catch (NoSuchElementException e){
        throw new NotFoundException(e.getMessage());
    }
    }

    @PutMapping(value = "/update/{id}/{buyer}")
    public DAOEstate updateEstate(@PathVariable int id, @PathVariable String buyer) {
        try {

            DAOEstate temp =estatesService.findById(Integer.valueOf(id)).get();
            temp.setBuyers(buyer);
            temp.setState(true);
            return  estatesService.save(temp);
        }catch (NoSuchElementException e){
            throw new NotFoundException(e.getMessage());
        }
    }

    @Transactional(rollbackFor = InvalidRollbackInException.class)
    @PostMapping("/sold/{estateId}")
    public String Sold(@PathVariable("estateId") String estateId, @RequestParam String SalerName, @RequestParam(defaultValue = "0") double price, HttpServletRequest request) throws InvalidRollbackInException {
        DAOEstate sold = estatesService.findById(Integer.valueOf(estateId)).get();
//        sold.setSoldAt(new Date());
        sold.setBuyers(SalerName);
        if (price != 0)
            sold.setCost((int) price);
        try {
            transactionalService.canSave(estateId);
            sold.setState(true);
            estatesService.save(sold);
            ArrayList<DAOEstate> temp = (ArrayList<DAOEstate>) estatesService.findAll();
        } catch (InvalidRollbackInException e) {
           throw new RoleBackException(e.getMessage());
        }
        return "Done";
    }


}
