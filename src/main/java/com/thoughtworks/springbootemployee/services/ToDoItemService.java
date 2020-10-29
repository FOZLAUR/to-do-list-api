package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.entity.ToDoItem;
import com.thoughtworks.springbootemployee.repositories.ToDoItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoItemService {
    private final ToDoItemRepository toDoItemRepository;

    public ToDoItemService(ToDoItemRepository toDoItemRepository) {
        this.toDoItemRepository = toDoItemRepository;
    }

    public ToDoItem createToDoItem(ToDoItem newToDoItem){
        return toDoItemRepository.save(newToDoItem);
    }

    public List<ToDoItem> getAllToDoItems() {
        return toDoItemRepository.findAll();
    }

    public ToDoItem updateToDoItem(int taskId) {
        ToDoItem toDoItem = toDoItemRepository.findById(taskId).orElse(null);
        toDoItem.setDone(!toDoItem.isDone());
        return toDoItemRepository.save(toDoItem);
    }

    public void deleteToDoItem(int taskId) {
        toDoItemRepository.deleteById(taskId);
    }

    public ToDoItem getToDoItemById(int taskId) {
        return toDoItemRepository.findById(taskId).orElse(null);
    }
}
