package com.javainuse;

import com.javainuse.controller.EstatesController;
import com.javainuse.controller.ParameterController;
import com.javainuse.model.DAOEstate;
import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Parameter;
import java.sql.Date;

//import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class EstateApplicationTests {

    @Autowired
    private EstatesController controller;

    private final ParameterController parameterService = Mockito.mock(ParameterController.class);

    @Test
    void contextLoads() {
    }

    @Test
    void StateAdd() {
//        DAOEstate estateModel = new DAOEstate("State One",15000,parameterService.getStocks());
//        service.addEstate(estateModel);

//        DAOEstate estateModel2 = service.getEstateById(estateModel.getId().toString());
//        assertEquals("State One", estateModel2.getName());
    }

    @Test
    void StateUpdate() {
//        DAOEstate estateModel = new DAOEstate("State One",15000);
//        service.addEstate(estateModel);

//        DAOEstate estateModel2 = service.getEstateById(estateModel.getId().toString());
//        estateModel2.setName("State One Edited");
//        service.addEstate(estateModel2);

//        DAOEstate estateModel3 = service.getEstateById(estateModel2.getId().toString());
//
//        assertEquals("State One Edited", estateModel3.getName());
    }

    @Test
    void StateDelete() {
//        DAOEstate estateModel = new DAOEstate("State One",15000);
//        service.addEstate(estateModel);
//
//        DAOEstate estateModel2 = service.getEstateById(estateModel.getId().toString());
//
//        assertNotNull(estateModel2);
//        service.deleteEstate(estateModel2.getId().toString());
//
//        DAOEstate estateModel3 = service.getEstateById(estateModel.getId().toString());
//        assertNull(estateModel3);
    }
}