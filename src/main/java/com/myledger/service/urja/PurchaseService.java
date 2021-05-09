package com.myledger.service.urja;

import com.myledger.model.urja.Purchase;
import com.myledger.repository.urja.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    public List<Purchase> listAllPurchase() {
        return purchaseRepository.findAll();
    }

    public void savePurchase(Purchase purchase){
        purchaseRepository.save(purchase);

    }

    public List<Purchase> listPurchaseByEnterpriseId(int enterpriseId){
        return purchaseRepository.findByEnterpriseId(enterpriseId);
    }

    public List<Purchase> listPurchaseByBillDateRange(Date billDateStart, Date billDateEnd){
        return purchaseRepository.findAllByBillDateBetween(billDateStart, billDateEnd);
    }
    public List<Purchase> listPurchaseByBillDateAfter(Date billDate){
        return purchaseRepository.findAllByBillDateAfter(billDate);
    }

}

