package com.myledger.repository.urja;
import com.myledger.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

    List<Transaction> findByEnterpriseIdOrderByTransactionDateAsc(long enterpriseId);
}
