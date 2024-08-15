package com.example.demo.repository;

import com.example.demo.entity.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Long> {
    InsurancePolicy findByPolicyNumber(String policyNumber);
}
