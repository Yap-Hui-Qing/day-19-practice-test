package vttp.batchb.ssf.day19_practice_test.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class Task {
    
    @NotNull(message = "ID cannot be null")
    @NotEmpty(message = "ID cannot be empty")
    @Size(max = 50,message = "Maxmimum length is 50")
    private String id;

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 10, max = 50, message = "Name should be between 10 and 50 characters")
    private String name;

    @Size(max = 255, message = "Maximum length is 255")
    private String description;

    @DateTimeFormat(pattern = "E, MM/dd/yyyy")
    @FutureOrPresent(message = "Date must be equals to or greater than today")
    private Date dueDate;
    private String priority;
    private String status;
    private Date createdAt;
    private Date updatedAt;
    
    @Override
    public String toString() {
        return "Task [id=" + id + ", name=" + name + ", description=" + description + ", dueDate=" + dueDate
                + ", priority=" + priority + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt="
                + updatedAt + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Task(String id, String name, String description, Date dueDate, String priority, String status,
            Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Task toTask(String json){
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject obj = reader.readObject();
        
        SimpleDateFormat sdf = new SimpleDateFormat("E, MM/dd/yyyy");
        Long epochmillisecondsdueDate = Long.parseLong(obj.getJsonNumber("due_date").toString());
        Date dueDate = new Date(epochmillisecondsdueDate);

        Long epochmillisecondscreatedDate = Long.parseLong(obj.getJsonNumber("created_at").toString());
        Date createdDate = new Date(epochmillisecondscreatedDate);

        Long epochmillisecondsupdatedDate = Long.parseLong(obj.getJsonNumber("updated_at").toString());
        Date updatedDate = new Date(epochmillisecondsupdatedDate);

        Task task = new Task(obj.getString("id"),
        obj.getString("name"),
        obj.getString("description"),
        dueDate, obj.getString("priority_level"),
        obj.getString("status"), createdDate, updatedDate);

        return task;
    }

}
