package com.thoughtworks.springbootemployee.controllers;


import com.thoughtworks.springbootemployee.dto.ToDoItemMapper;
import com.thoughtworks.springbootemployee.dto.ToDoItemRequest;
import com.thoughtworks.springbootemployee.dto.ToDoItemResponse;
import com.thoughtworks.springbootemployee.entity.ToDoItem;
import com.thoughtworks.springbootemployee.services.ToDoItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/todos")
public class ToDoItemController {
    private final ToDoItemService toDoItemService;
    private final ToDoItemMapper toDoItemMapper;

    public ToDoItemController(ToDoItemService toDoItemService, ToDoItemMapper toDoItemMapper) {
        this.toDoItemService = toDoItemService;
        this.toDoItemMapper = toDoItemMapper;
    }

    //CREATE
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ToDoItemResponse createToDoItem(@RequestBody ToDoItemRequest request){
        ToDoItem toDoItem = toDoItemMapper.toEntity(request);
        return toDoItemMapper.toResponse(toDoItemService.createToDoItem(toDoItem));
    }

    // READ
    @GetMapping()
    public List<ToDoItemResponse> getAllToDoItems() {
        List<ToDoItem> toDoItemList = toDoItemService.getAllToDoItems();
        return toDoItemList.stream().map(toDoItemMapper::toResponse).collect(Collectors.toList());
    }

    // UPDATE
    @PutMapping("/{toDoId}")
    public ToDoItemResponse updateToDoItem(@PathVariable int toDoId){
        ToDoItem updatedToDoItem = toDoItemService.updateToDoItem(toDoId);
        return toDoItemMapper.toResponse(updatedToDoItem);
    }

    // DELETE
    @DeleteMapping("/{toDoId}")
    public void deleteToDoItem(@PathVariable int toDoId){
        toDoItemService.deleteToDoItem(toDoId);
    }
}
