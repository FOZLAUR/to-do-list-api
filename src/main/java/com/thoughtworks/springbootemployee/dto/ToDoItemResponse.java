package com.thoughtworks.springbootemployee.dto;

public class ToDoItemResponse {
    private int toDoId;
    private String text;
    private boolean done;

    public ToDoItemResponse() { }

    public ToDoItemResponse(int toDoId, String text, boolean done) {
        this.toDoId = toDoId;
        this.text = text;
        this.done = done;
    }

    public int getToDoId() {
        return toDoId;
    }

    public void setToDoId(int toDoId) {
        this.toDoId = toDoId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
