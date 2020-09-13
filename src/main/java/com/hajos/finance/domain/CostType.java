package com.hajos.finance.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class CostType {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long id;
    @ManyToOne
    private CostTypeCategory category;
    @JsonBackReference
    @OneToMany(mappedBy = "costType")
    private List<Cost> cost;
    private String name;

    public CostType(){}

    public CostType(CostTypeCategory category, List<Cost> cost, String name) {
        this.category = category;
        this.cost = cost;
        this.name = name;
    }

    public CostTypeCategory getCategory() {
        return category;
    }
    public void setCategory(CostTypeCategory category) {
        this.category = category;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<Cost> getCost() {
        return cost;
    }
    public void setCost(List<Cost> cost) {
        this.cost = cost;
    }
}
