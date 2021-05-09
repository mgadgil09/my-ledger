package com.myledger.controller.urja;

import com.myledger.model.*;
import com.myledger.model.Enums.PaymentSourceType;
import com.myledger.model.Enums.TransactionType;
import com.myledger.model.urja.*;
import com.myledger.service.urja.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PaymentEntryController {

    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PaymentSourceService paymentSourceService;
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private TransactionService transactionService;

    private List<Enterprise> addEnterpriseDropdownAttributes(){
        return enterpriseService.getAllEntperises();
    }

    private List<PaymentSourceType> addPaymentSourceTypeDropdownAttributes(){
        List<PaymentSourceType> paymentSourceTypes = new ArrayList<PaymentSourceType>();
        for(PaymentSourceType paymentSourceType : PaymentSourceType.values()){
            paymentSourceTypes.add(paymentSourceType);
        }
        return paymentSourceTypes;
    }

    private void createPaymentSourceEntry(PaymentEntry paymentEntry){
        PaymentSource paymentSource = new PaymentSource();
        paymentSource.setSourceCode(paymentEntry.getSelectedPaymentSource().name());
        paymentSource.setSourceName(paymentEntry.getSelectedPaymentSource().getPaymentSourceType());
        paymentSourceService.savePaymentSource(paymentSource);
    }

    private Payment createPaymentEntry(PaymentEntry paymentEntry){
        Payment payment = paymentEntry.getPayment();
        payment.setEnterpriseId(paymentEntry.getSelectedEnterpriseId());
        payment.setPaymentMode(paymentEntry.getSelectedPaymentSource().name());
        paymentService.savePayment(payment);
        return payment;
    }

    private void createTransactionEntry(Payment payment) throws Exception{
        Transaction transaction = new Transaction();
        transaction.setEnterpriseId(payment.getEnterpriseId());
        transaction.setTransactionDate(payment.getPaymentDate());
        transaction.setTransactionType(TransactionType.PAYMENT.name());
        transaction.setTransactionAmount(payment.getAmount());
        transactionService.saveTransaction(transaction);
    }

    private void createBalanceEntry(PaymentEntry paymentEntry){
        Balance balance = new Balance();
        balance.setEnterpriseId(paymentEntry.getSelectedEnterpriseId());
        Enterprise enterprise = enterpriseService.getEnterpriseById((int) paymentEntry.getSelectedEnterpriseId());
        boolean enterpriseExists = enterpriseService.existsEnterprise(enterprise.getEnterpriseName());
        if(enterpriseExists){
            // update balance
            double currentBalance = balanceService.getBalance(paymentEntry.getSelectedEnterpriseId()).getAmountDue();
            balance.setAmountDue(currentBalance + paymentEntry.getPayment().getAmount());
            balanceService.updateBalance(balance.getAmountDue(), balance.getEnterpriseId());
        }
        else{
            // create new balance entry
            balance.setAmountDue(paymentEntry.getPayment().getAmount());
            balanceService.saveBalance(balance);
        }
    }
    @GetMapping("/urja-innovations/submit-payment")
    public String showPaymentForm(Model model) {
        model.addAttribute("paymentEntry", new PaymentEntry());
        model.addAttribute("enterprises", addEnterpriseDropdownAttributes());
        model.addAttribute("paymentSources", addPaymentSourceTypeDropdownAttributes());
        return "urja_innovations/payment_entry";
    }

    @PostMapping("/urja-innovations/process_payment")
    public String processpayment(@ModelAttribute("paymentEntry") PaymentEntry paymentEntry, Model model) throws Exception{

        // implement your own registration logic here...
        /*
        1. Check if bank exists. If yes, go to step 3
        2. Create payment source (bank) entry
        3. Create Payment Entry
        4. Create Balance Entry
        5. Return ack
         */
        long selectedEnterpriseId = paymentEntry.getSelectedEnterpriseId();
        PaymentSourceType selectedPaymentSource = paymentEntry.getSelectedPaymentSource();
        model.addAttribute("enterprises", addEnterpriseDropdownAttributes());
        model.addAttribute("paymentSources", addPaymentSourceTypeDropdownAttributes());
        if(selectedEnterpriseId != 0 && selectedPaymentSource != null){
            // Create Payment source entry
            boolean paymentSourceExists = paymentSourceService.existsPaymentSourceBySourceCode(selectedPaymentSource.name());
            if(!paymentSourceExists){
                createPaymentSourceEntry(paymentEntry);
            }
            // Create payment entry
            Payment payment = createPaymentEntry(paymentEntry);

            // Create transaction entry
            createTransactionEntry(payment);
            // Create balance Entry
            createBalanceEntry(paymentEntry);
        }
        return "urja_innovations/payment_entry_success";
    }
}