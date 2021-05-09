package com.myledger.model.urja;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.text.SimpleDateFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Purchase {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "bill_no")
    private String billNo;
    @Column(name = "bill_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date billDate;

    @Column(name = "enterprise_id")
    private long enterpriseId;
    @Column(name = "amount")
    private float amount;
    @Column(name = "amount_vat")
    private float amountVat;

    public long getId() {
        return id;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) throws Exception
    {

        this.billDate = new SimpleDateFormat("yyyy-MM-dd").parse(billDate);
    }

    public long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getAmountVat() {
        return amountVat;
    }

    public void setAmountVat(float amountVat) {
        this.amountVat = amountVat;
    }

    public float getTotalAmount() {
        return this.amount + this.amountVat;
    }


}

