package com.myledger.controller.urja;

import com.myledger.model.urja.AccountBalance;
import com.myledger.model.urja.Enterprise;
import com.myledger.service.urja.BalanceService;
import com.myledger.service.urja.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AccountBalanceController {
    @Autowired
    private BalanceService balanceService;

    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping("/urja-innovations/account-balance")
    public String showAccountBalanceForm(Model model){
        AccountBalance accountBalance = new AccountBalance();
        model.addAttribute("accountBalance", accountBalance);
        List<Enterprise> enterprises = enterpriseService.getAllEntperises();
        model.addAttribute("enterprises", enterprises);
        return "urja_innovations/account_balance";
    }

    @PostMapping("/urja-innovations/account-balance")
    public String getAccountBalance(@ModelAttribute("accountBalance") AccountBalance accountBalance, Model model) throws Exception{
        long selectedEnterpriseId = accountBalance.getSelectedEnterpriseId();
        List<Enterprise> enterprises = enterpriseService.getAllEntperises();
        model.addAttribute("enterprises", enterprises);
        if(selectedEnterpriseId != 0) {
            String selectedEnterpriseName = enterpriseService.getEnterpriseById((int) selectedEnterpriseId).getEnterpriseName();
            double balanceDue = balanceService.getBalance(selectedEnterpriseId).getAmountDue();
            model.addAttribute("supplier", selectedEnterpriseName);
            model.addAttribute("balanceDue", "" + -balanceDue);
            model.addAttribute("show_balance", true);
        }
        return "urja_innovations/account_balance";
    }
}

