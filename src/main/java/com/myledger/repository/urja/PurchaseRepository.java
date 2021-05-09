package com.myledger.repository.urja;

import com.myledger.model.urja.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer>{

    List<Purchase> findAllByBillDateBetween(
            Date billDateStart,
            Date billDateEnd);

    List<Purchase> findAllByBillDateAfter(
            Date billDate);

    List<Purchase> findByEnterpriseId(int enterpriseId);
}
