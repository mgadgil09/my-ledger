package com.myledger.repository.urja;

import com.myledger.model.urja.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Integer> {

    List<Enterprise> findByEnterpriseNameLikeIgnoreCase(String enterpriseName);
    Enterprise findById(long id);
    Enterprise findIdByEnterpriseName(String enterpriseName);
    Boolean existsByEnterpriseNameLikeIgnoreCase(String enterpriseName);
}
