package com.example.main.core;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "taskmgmt")
@NamedQuery(
        name = "com.example.main.core.TaskMgmt.findAll",
        query = "SELECT t FROM TaskMgmt t"
)
public class TaskMgmt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long id;

    @Column(nullable = false)
    private String taskName;

    @Column(nullable = false)
    private boolean isCompleted;

    @Column(nullable = false)
    private Date dueDate;

    public TaskMgmt() {

    }
    public TaskMgmt(String taskName, boolean isCompleted, Date dueDate) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
        this.dueDate = dueDate;
    }
    public TaskMgmt(long id, String taskName, boolean isCompleted, Date dueDate) {
        this.id = id;
        this.taskName = taskName;
        this.isCompleted = isCompleted;
        this.dueDate = dueDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof TaskMgmt)) {
            return false;
        }

        TaskMgmt taskMgmt = (TaskMgmt) o;

        System.out.println(dueDate.compareTo(taskMgmt.dueDate));

        System.out.println("last compilation");

        System.out.println(id == taskMgmt.getId() &&
                Objects.equals(taskName, taskMgmt.getTaskName()) &&
                isCompleted == taskMgmt.isCompleted &&
                dueDate.compareTo(taskMgmt.getDueDate()) == 0);

        System.out.println("Task Name: "+ taskName + " vs "+ taskMgmt.getTaskName());
        System.out.println("id: "+ id + " vs "+ taskMgmt.getId());
        System.out.println("IsCompleted: "+ isCompleted + " vs "+ taskMgmt.isCompleted());

        String pattern = "yyyy/MM/dd HH:mm:ss";

// Create an instance of SimpleDateFormat used for formatting
// the string representation of date according to the chosen pattern
        DateFormat df = new SimpleDateFormat(pattern);


        System.out.println("dueDate: "+ df.format(dueDate) + " vs "+ df.format(taskMgmt.getDueDate()));



        return id == taskMgmt.id &&
                Objects.equals(taskName, taskMgmt.taskName) &&
                isCompleted == taskMgmt.isCompleted &&
                dueDate.compareTo(taskMgmt.dueDate) == 0;
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(id, taskName, dueDate, isCompleted);
        System.out.println(hash);
        return hash;
    }


}
