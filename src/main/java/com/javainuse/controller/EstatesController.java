package com.javainuse.controller;


import com.javainuse.dao.EstatesRepository;
import com.javainuse.model.DAOEstate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private EstatesRepository estatesService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<DAOEstate>> home() {
        List<DAOEstate> result = (List<DAOEstate>) estatesService.findAll();
        return new ResponseEntity<List<DAOEstate>>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DAOEstate> findById(@PathVariable int id) {
        DAOEstate result = estatesService.findById(Integer.valueOf(id)).get();
        return new ResponseEntity<DAOEstate>(result, HttpStatus.OK);
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
        estatesService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/update/{id}/{buyer}")
    public DAOEstate updateEstate(@PathVariable int id, @PathVariable String buyer) {
        DAOEstate temp =estatesService.findById(id).get();
        temp.setBuyers(buyer);
        temp.setState(true);
        return  estatesService.save(temp);
    }


}
