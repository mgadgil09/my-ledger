package com.myledger.service.urja;

import com.myledger.model.urja.Balance;
import com.myledger.repository.urja.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BalanceService {
    @Autowired
    private BalanceRepository balanceRepository;

    public void saveBalance(Balance balance){
        balanceRepository.save(balance);

    }
    public Balance getBalance(long enterpriseId){
        return balanceRepository.findByEnterpriseId(enterpriseId);
    }
    public void updateBalance(double amount, long enterpriseId){
        balanceRepository.updateBalanceForEnterprise(amount, enterpriseId);
    }

}
