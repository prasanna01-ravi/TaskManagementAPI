package com.example.main.core;

import javax.persistence.Column;

public class TaskCompletionViewModel {

    @Column(nullable = false)
    private boolean isCompleted;

    public TaskCompletionViewModel(){ }

    public TaskCompletionViewModel(boolean isCompleted){
        this.isCompleted = isCompleted;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
