package com.example.recyclememoapp.model;

import java.util.Date;

public class RowData {

    private int id;
    private String detail;
    private String title;
    private int priority;
    private Date created_datetime;
    private Date updated_datetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getCreated_datetime() {
        return created_datetime;
    }

    public void setCreated_datetime(Date created_datetime) {
        this.created_datetime = created_datetime;
    }

    public Date getUpdated_datetime() {
        return updated_datetime;
    }

    public void setUpdated_datetime(Date updated_datetime) {
        this.updated_datetime = updated_datetime;
    }
}
