package com.hajos.finance.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Cost {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @JsonProperty("costType")
    @ManyToOne
    private CostType costType;
    @JsonProperty("paymentMethod")
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = true)
    private PaymentMethod paymentMethod;
    @JsonProperty("paymentPeriod")
    @Column(nullable = false)
    private Date paymentPeriod; //Tárgyidőszak
    @JsonProperty("paymentDeadline")
    @Column(nullable = true)
    private Date paymentDeadline; //Fizetési határidő
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("paymentTime")
    @Column(nullable = true)
    private Date paymentTime; //Befizetési idő
    @JsonProperty("paid")
    private Boolean isPaid;

    public Cost(){}

    public Cost(CostType costType, PaymentMethod paymentMethod, Date paymentPeriod, Date paymentDeadline, Double amount, Date paymentTime, Boolean isPaid) {
        this.costType = costType;
        this.paymentMethod = paymentMethod;
        this.paymentPeriod = paymentPeriod;
        this.paymentDeadline = paymentDeadline;
        this.amount = amount;
        this.paymentTime = paymentTime;
        this.isPaid = isPaid;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public CostType getCostType() {
        return costType;
    }
    public void setCostType(CostType costType) {
        this.costType = costType;
    }
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public Date getPaymentPeriod() {
        return paymentPeriod;
    }
    public void setPaymentPeriod(Date paymentPeriod) {
        this.paymentPeriod = paymentPeriod;
    }
    public Date getPaymentDeadline() {
        return paymentDeadline;
    }
    public void setPaymentDeadline(Date paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public Date getPaymentTime() {
        return paymentTime;
    }
    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }
    public Boolean getPaid() {
        return isPaid;
    }
    public void setPaid(Boolean paid) {
        isPaid = paid;
    }
}
