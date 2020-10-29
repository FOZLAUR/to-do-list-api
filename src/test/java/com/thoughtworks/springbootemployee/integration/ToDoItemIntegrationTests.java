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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
                .andExpect(jsonPath("$[0].id").isNumber())
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
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.text").value("destoroyah"))
                .andExpect(jsonPath("$.done").value(false));
    }

    @Test
    void should_update_todo_when_update_given_todoId() throws Exception {
        //given
        String updatedTodo = "{\n" +
                "}";

        //when
        ToDoItem toDoItem = toDoItemRepository.save(new ToDoItem(2, "Chels"));

        //then
        mockMvc.perform(put(TODOS_URI + "/" + toDoItem.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedTodo))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.done").value(true));

        ToDoItem foundToDoItem = toDoItemRepository.findById(toDoItem.getId()).orElse(null);
        Assertions.assertTrue(foundToDoItem.isDone());
    }

    @Test
    void should_delete_todo_when_delete_given_todoId() throws Exception {

        //when
        ToDoItem toDoItem = toDoItemRepository.save(new ToDoItem(2, "Chels"));

        //then
        mockMvc.perform(delete(TODOS_URI + "/" + toDoItem.getId()));

        ToDoItem foundToDoItem = toDoItemRepository.findById(toDoItem.getId()).orElse(null);
        Assertions.assertNull(foundToDoItem);
    }
}
