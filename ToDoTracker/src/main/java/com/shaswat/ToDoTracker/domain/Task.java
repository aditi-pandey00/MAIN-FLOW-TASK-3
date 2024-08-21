package com.shaswat.ToDoTracker.domain;

import org.springframework.data.annotation.Id;


public class Task {
    @Id
    private  int taskId;
    private String taskName;
    private String taskContent;



    public Task() {
    }

    public Task(int taskId, String taskName, String taskContent) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskContent = taskContent;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskContent='" + taskContent + '\'' +
                '}';
    }
}
