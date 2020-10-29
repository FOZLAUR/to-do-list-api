package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.entity.ToDoItem;
import com.thoughtworks.springbootemployee.repositories.ToDoItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ToDoItemIntegrationTests {
    public static final String TODOS_URI = "/todos";
    @Autowired
    private ToDoItemRepository toDoItemRepository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void should_get_all_todos_when_get_all() throws Exception{
        ToDoItem toDoItem = new ToDoItem(1,"aaaaaa");
        toDoItemRepository.save(toDoItem);

        mockMvc.perform(get(TODOS_URI))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].toDoId").isNumber())
                .andExpect(jsonPath("$[0].text").value("aaaaaa"))
                .andExpect(jsonPath("$[0].done").value(false));
    }

    @Test
    void should_create_todo_when_create_given_todo_request() throws Exception {
        //given
        String employeeAsJson = "{\n" +
            "\"text\" : \"destoroyah\"\n" +
        "}";

        //when
        //then
        mockMvc.perform(post(TODOS_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeAsJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.toDoId").isNumber())
                .andExpect(jsonPath("$.text").value("destoroyah"))
                .andExpect(jsonPath("$.done").value(false));
    }
}
