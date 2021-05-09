package com.myledger.service.urja;

import com.myledger.model.urja.Payment;
import com.myledger.repository.urja.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public void savePayment(Payment payment){
        paymentRepository.save(payment);

    }

    public List<Payment> listPaymentByEnterpriseId(int enterpriseId){
        return paymentRepository.findByEnterpriseId(enterpriseId);
    }

}

