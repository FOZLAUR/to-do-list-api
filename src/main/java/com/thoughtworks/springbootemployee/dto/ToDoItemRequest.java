package com.thoughtworks.springbootemployee.dto;

public class ToDoItemRequest {
    private String text;

    public ToDoItemRequest() { }

    public ToDoItemRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

//    public boolean isDone() {
//        return done;
//    }
//
//    public void setDone(boolean done) {
//        this.done = done;
//    }
}
