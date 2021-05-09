package com.myledger.repository.urja;
import com.myledger.model.urja.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BalanceRepository extends JpaRepository<Balance, Integer> {

    Balance findByEnterpriseId(long enterpriseId);
    @Modifying
    @Query("update Balance bal set bal.amountDue = :amount where bal.enterpriseId = :enterprise_id")
    void updateBalanceForEnterprise(@Param("amount") double amount, @Param("enterprise_id") long enterpriseId);
}
