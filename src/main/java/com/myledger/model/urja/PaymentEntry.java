package com.myledger.model.urja;

import com.myledger.model.Enums.PaymentSourceType;

public class PaymentEntry {
    private Payment payment;
    private Enterprise enterprise;
    private PaymentSource sourceName;
    private long selectedEnterpriseId;
    private PaymentSourceType selectedPaymentSource;

    public PaymentSourceType getSelectedPaymentSource() {
        return selectedPaymentSource;
    }

    public void setSelectedPaymentSource(PaymentSourceType selectedPaymentSource) {
        this.selectedPaymentSource = selectedPaymentSource;
    }

    public long getSelectedEnterpriseId() {
        return selectedEnterpriseId;
    }

    public void setSelectedEnterpriseId(long selectedEnterpriseId) {
        this.selectedEnterpriseId = selectedEnterpriseId;
    }

    public PaymentSource getSourceName() {
        return sourceName;
    }

    public void setSourceName(PaymentSource sourceName) {
        this.sourceName = sourceName;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }


}
