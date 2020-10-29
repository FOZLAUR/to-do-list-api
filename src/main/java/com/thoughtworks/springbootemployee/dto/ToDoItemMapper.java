package com.thoughtworks.springbootemployee.dto;

import com.thoughtworks.springbootemployee.entity.ToDoItem;
import org.springframework.stereotype.Component;

@Component
public class ToDoItemMapper {
    public ToDoItemResponse toResponse(ToDoItem toDoItem){
        ToDoItemResponse toDoItemResponse = new ToDoItemResponse();

        toDoItemResponse.setId(toDoItem.getId());
        toDoItemResponse.setText(toDoItem.getText());
        toDoItemResponse.setDone(toDoItem.isDone());

        return toDoItemResponse;
    }

    public ToDoItem toEntity(ToDoItemRequest toDoItemRequest){
        ToDoItem toDoItem = new ToDoItem();

        toDoItem.setText(toDoItemRequest.getText());
        toDoItem.setDone(toDoItemRequest.isDone());

        return toDoItem;
    }
}
