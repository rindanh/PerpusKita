package com.example.perpuskita;

import java.util.Date;


public class Borrow {
    private Integer id;
    private Integer bookId;
    private Integer membinterId;
    private Integer numDay;
    private Date createdAt;
    private Date updatedAt;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getMembinterId() {
        return membinterId;
    }

    public void setMembinterId(Integer membinterId) {
        this.membinterId = membinterId;
    }

    public Integer getNumDay() {
        return numDay;
    }

    public void setNumDay(Integer numDay) {
        this.numDay = numDay;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
