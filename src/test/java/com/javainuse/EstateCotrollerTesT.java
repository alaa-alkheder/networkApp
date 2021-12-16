package com.javainuse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javainuse.model.DAOEstate;
import com.javainuse.service.EstateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * Created by IntelliJ IDEA.
 * User: Alaa Alkheder
 * Email:alaa-alkheder@outlook.com
 * Github:alaa-alkheder
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EstateCotrollerTesT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EstateService estateService;

    @Test
    public void addNewEstateTest() throws Exception {
        DAOEstate daoEstate=new DAOEstate();
        daoEstate.setEstatesName("first Estate");
        daoEstate.setCost(500);
        daoEstate.setNumberOfShares(5);

        given(estateService.createNewEstate(Mockito.any(DAOEstate.class))).willReturn(daoEstate);
        ObjectMapper mapper=new ObjectMapper();
        mockMvc.perform(post("/api/v1/estates")
        .contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(daoEstate)))
                .andExpect(status().isCreated());
     }
     @Test
    public void deleteEstate() {
        DAOEstate daoEstate = new DAOEstate();
        daoEstate.setEstatesName("test");
        daoEstate.setId(5858);
      estateService.createNewEstate(daoEstate);

        estateService.deleteEstate((int) daoEstate.getId());
        DAOEstate dao = estateService.findById((int) daoEstate.getId());
        assertEquals(null, dao);
    }
    @Test
    public void updateEstate() {
        DAOEstate daoEstate = new DAOEstate();
        daoEstate.setEstatesName("test");
        daoEstate.setId(5858);
      estateService.createNewEstate(daoEstate);

        DAOEstate dao =    estateService.updateEstate((int) daoEstate.getId(),"samer");
         assertEquals(null, dao);
    }

}
