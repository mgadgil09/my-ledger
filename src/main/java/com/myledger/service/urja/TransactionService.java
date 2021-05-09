package com.myledger.service.urja;

import com.myledger.model.Transaction;
import com.myledger.repository.urja.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public void saveTransaction(Transaction transaction){
        transactionRepository.save(transaction);

    }

    public List<Transaction> listTransactionByEnterpriseId(int enterpriseId){
        return transactionRepository.findByEnterpriseId(enterpriseId);
    }

}

