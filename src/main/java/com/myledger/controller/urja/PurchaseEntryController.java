package com.myledger.controller.urja;

import com.myledger.model.*;
import com.myledger.model.Enums.TransactionType;
import com.myledger.model.urja.Balance;
import com.myledger.model.urja.Enterprise;
import com.myledger.model.urja.Purchase;
import com.myledger.model.urja.PurchaseEntry;
import com.myledger.service.urja.BalanceService;
import com.myledger.service.urja.EnterpriseService;
import com.myledger.service.urja.PurchaseService;
import com.myledger.service.urja.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


@Controller
public class PurchaseEntryController {

    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/urja-innovations/submit-purchase")
    public String showPurchaseForm(Model model) {
        model.addAttribute("purchaseEntry", new PurchaseEntry());
        return "urja_innovations/purchase_entry";
    }

    private void createTransactionEntry(Purchase purchase) throws Exception{
        Transaction transaction = new Transaction();
        transaction.setEnterpriseId(purchase.getEnterpriseId());
        transaction.setTransactionDate(purchase.getBillDate());
        transaction.setTransactionType(TransactionType.PURCHASE.name());
        transaction.setTransactionAmount(purchase.getTotalAmount());
        transactionService.saveTransaction(transaction);
    }


    @PostMapping("/urja-innovations/process_purchase")
    public String processPurchase(@ModelAttribute("purchaseEntry") PurchaseEntry purchaseEntry) throws Exception{

        // implement your own registration logic here...
        /*
        1. Check if enterprise exists. If yes, go to step 3
        2. Create enterprise entry
        3. Create Purchase Entry
        4. Create Transaction Entry
        5. Create Balance Entry
        6. Return ack
         */
        Enterprise enterprise = purchaseEntry.getEnterprise();
        boolean enterpriseExists = enterpriseService.existsEnterprise(enterprise.getEnterpriseName());
        if(!enterpriseExists){
            enterpriseService.saveEnterprise(enterprise);

        }
        long enterpriseId = enterpriseService.getEnterpriseId(enterprise.getEnterpriseName());

        // Create Purchase Entry
        Purchase purchase = purchaseEntry.getPurchase();
        purchase.setEnterpriseId(enterpriseId);
        purchaseService.savePurchase(purchase);

        // Create Transaction Entry
        createTransactionEntry(purchase);

        // Create Balance Entry
        Balance balance = new Balance();
        balance.setEnterpriseId(enterpriseId);

        if(enterpriseExists){
            // update balance
            double currentBalance = balanceService.getBalance(enterpriseId).getAmountDue();
            balance.setAmountDue(currentBalance - purchase.getTotalAmount());
            balanceService.updateBalance(balance.getAmountDue(), balance.getEnterpriseId());
        }
        else{
            // create new balance entry
            balance.setAmountDue(-purchase.getTotalAmount());
            balanceService.saveBalance(balance);
        }

        return "urja_innovations/purchase_entry_success";
    }
}