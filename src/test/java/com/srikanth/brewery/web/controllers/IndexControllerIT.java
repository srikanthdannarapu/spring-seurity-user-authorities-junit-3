package com.srikanth.brewery.web.controllers;

import com.srikanth.brewery.repositories.BeerInventoryRepository;
import com.srikanth.brewery.repositories.BeerRepository;
import com.srikanth.brewery.repositories.CustomerRepository;
import com.srikanth.brewery.services.BeerService;
import com.srikanth.brewery.services.BreweryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest
public class IndexControllerIT extends BaseIT {
    @MockBean
    BeerRepository beerRepository;

    @MockBean
    BeerInventoryRepository beerInventoryRepository;

    @MockBean
    BreweryService breweryService;

    @MockBean
    CustomerRepository customerRepository;

    @MockBean
    BeerService beerService;
    @Test
    void testGetIndexSlash() throws Exception{
        mockMvc.perform(get("/" ))
                .andExpect(status().isOk());
    }
}