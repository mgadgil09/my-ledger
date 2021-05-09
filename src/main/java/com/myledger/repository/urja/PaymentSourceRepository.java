package com.myledger.repository.urja;

import com.myledger.model.urja.PaymentSource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentSourceRepository extends JpaRepository<PaymentSource, Integer>{

    PaymentSource findBySourceCode(String sourceCode);
    Boolean existsBySourceCodeLikeIgnoreCase(String sourceCode);

}
