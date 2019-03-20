package com.kubatov.todo;

import java.io.Serializable;

public class Task implements Serializable {
    String title;
    String description;

    public Task() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
