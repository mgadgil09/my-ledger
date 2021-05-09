package com.myledger.service.urja;
import com.myledger.model.urja.Enterprise;
import com.myledger.repository.urja.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EnterpriseService {
    @Autowired
    private EnterpriseRepository enterpriseRepository;


    public void saveEnterprise(Enterprise enterprise){
        enterpriseRepository.save(enterprise);

    }

    public long getEnterpriseId(String enterpriseName){
        return enterpriseRepository.findIdByEnterpriseName(enterpriseName).getId();

    }
    public Enterprise getEnterprise(String enterpriseName){
        return enterpriseRepository.findByEnterpriseNameLikeIgnoreCase(String.format("%%%s%%",enterpriseName)).get(0);
    }

    public Enterprise getEnterpriseById(int id){
        return enterpriseRepository.findById(id);
    }
    public Boolean existsEnterprise(String enterpriseName){
        return enterpriseRepository.existsByEnterpriseNameLikeIgnoreCase(enterpriseName);
    }

    public List<Enterprise> getAllEntperises(){
        return enterpriseRepository.findAll();
    }

}
