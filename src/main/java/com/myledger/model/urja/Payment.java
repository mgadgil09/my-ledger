package com.myledger.model.urja;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Column(name = "enterprise_id")
    private long enterpriseId;
    @Column(name = "payment_mode")
    private String paymentMode;
    @Column(name = "payment_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date paymentDate;
    @Column(name = "amount")
    private double amount;
    public long getId() {
        return id;
    }

    public long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) throws Exception{

        this.paymentDate = new SimpleDateFormat("yyyy-MM-dd").parse(paymentDate);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

