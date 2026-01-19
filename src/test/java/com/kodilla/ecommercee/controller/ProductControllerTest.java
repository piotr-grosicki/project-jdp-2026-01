package com.kodilla.ecommercee.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetAllProductsTest() throws Exception {
        //Given
        //When & Then
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void shouldGetProductTest() throws Exception {
        //When & Then
        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("P1"))
                .andExpect(jsonPath("$.description").value("D1"))
                .andExpect(jsonPath("$.price").value(0))
                .andExpect(jsonPath("$.groupId").value(1));
    }


    @Test
    void shouldAddProductTest() throws Exception {
        //Given
        String body = """
        {
          "id": 1,
          "name": "P1",
          "description": "D1",
          "price": 0,
          "groupId": 1
        }
        """;
        //When & Then
        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("P1"))
                .andExpect(jsonPath("$.description").value("D1"))
                .andExpect(jsonPath("$.price").value(0))
                .andExpect(jsonPath("$.groupId").value(1));
    }

    @Test
    void shouldUpdateProductTest() throws Exception {
        //Given
        String body = """
        {
          "id": 1,
          "name": "P1",
          "description": "D1",
          "price": 0,
          "groupId": 1
        }
        """;
        //When & Then
        mockMvc.perform(put("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("P1"))
                .andExpect(jsonPath("$.description").value("D1"))
                .andExpect(jsonPath("$.price").value(0))
                .andExpect(jsonPath("$.groupId").value(1));
    }

    @Test
    void shouldDeleteProductTest() throws Exception {
        //When & Then
        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isOk());
    }
}
