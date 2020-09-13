package com.hajos.finance.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class CostTypeCategory {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    @JsonBackReference
    @OneToMany(mappedBy = "category")
    private List<CostType> costTypes;

    public CostTypeCategory(){}

    public CostTypeCategory(String name, List<CostType> costTypes) {
        this.name = name;
        this.costTypes = costTypes;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<CostType> getCostTypes() {
        return costTypes;
    }
    public void setCostTypes(List<CostType> costTypes) {
        this.costTypes = costTypes;
    }
}
