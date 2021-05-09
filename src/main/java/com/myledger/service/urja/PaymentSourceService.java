package com.myledger.service.urja;

import com.myledger.model.urja.PaymentSource;
import com.myledger.repository.urja.PaymentSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PaymentSourceService {
    @Autowired
    private PaymentSourceRepository paymentSourceRepository;

    public void savePaymentSource(PaymentSource paymentSource){
        paymentSourceRepository.save(paymentSource);

    }

    public PaymentSource getPaymentSourceNameBySourceCode(String paymentSourceCode){
        return paymentSourceRepository.findBySourceCode(paymentSourceCode);
    }

    public List<PaymentSource> getAllPaymentSources(){
        return paymentSourceRepository.findAll();
    }

    public Boolean existsPaymentSourceBySourceCode(String sourceCode) {
        return paymentSourceRepository.existsBySourceCodeLikeIgnoreCase(sourceCode);
    }

}