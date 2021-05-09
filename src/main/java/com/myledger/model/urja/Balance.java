package com.myledger.model.urja;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Balance {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Column(name = "enterprise_id")
    private long enterpriseId;
    @Column(name = "amount_due")
    private double amountDue;

    public long getId() {
        return id;
    }

    public long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) { this.amountDue = amountDue; }

}
