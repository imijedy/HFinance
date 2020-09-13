package com.hajos.finance.domain;


public class ChartData {
    private Integer monthIndex;
    private Double amount;

    public ChartData (){}

    public ChartData(Integer monthIndex, Double amount) {
        this.monthIndex = monthIndex;
        this.amount = amount;
    }

    public Integer getMonthIndex() {
        return monthIndex;
    }

    public void setMonthIndex(Integer monthIndex) {
        this.monthIndex = monthIndex;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
