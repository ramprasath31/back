package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.InsurancePolicyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class InsurancePolicyService {

    @Autowired
    private InsurancePolicyRepository repository;

    public InsurancePolicy renewPolicy(String policyNumber, String email, String insuranceType, LocalDate renewalDate) {
        Optional<InsurancePolicy> existingPolicy = Optional.ofNullable(repository.findByPolicyNumber(policyNumber));

        if (existingPolicy.isPresent()) {
            InsurancePolicy policy = existingPolicy.get();
            policy.setEmail(email);
            policy.setInsuranceType(insuranceType);
            policy.setRenewalDate(renewalDate);
            return repository.save(policy);
        } else {
            // Create a new policy if it doesn't exist
            InsurancePolicy newPolicy = new InsurancePolicy(policyNumber, email, insuranceType, renewalDate);
            return repository.save(newPolicy);
        }
    }

    public InsurancePolicy getPolicyByNumber(String policyNumber) {
        return repository.findByPolicyNumber(policyNumber);
    }
}

