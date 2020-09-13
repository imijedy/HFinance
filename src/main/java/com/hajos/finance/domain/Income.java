package com.hajos.finance.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Income {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = true)
    private String information;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    private Date date;

    public Income(){}

    public Income(String information, Double amount, Date date) {
        this.information = information;
        this.amount = amount;
        this.date = date;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getInformation() {
        return information;
    }
    public void setInformation(String information) {
        this.information = information;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
