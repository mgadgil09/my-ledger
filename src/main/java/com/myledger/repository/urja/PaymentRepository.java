package com.myledger.repository.urja;
import com.myledger.model.urja.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

    List<Payment> findByEnterpriseId(int enterpriseId);
}
