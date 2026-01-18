package com.kodilla.ecommercee;

import com.google.gson.Gson;
import com.kodilla.ecommercee.controller.GroupController;
import com.kodilla.ecommercee.dto.CreateUpdateGroupDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GroupController.class)
public class GroupControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllGroups() throws Exception {
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/groups"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("groups list"));
    }

    @Test
    void testGetOneGroup() throws Exception {
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/groups/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("existing group"));
    }

    @Test
    void testAddGroup() throws Exception {
        //Given
        CreateUpdateGroupDto createUpdateGroupDto = new CreateUpdateGroupDto("Test group");
        Gson gson = new Gson();
        String requestBody = gson.toJson(createUpdateGroupDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test group"));
    }

    @Test
    void testUpdateGroup() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put("/groups/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("updated group"));
    }
}
