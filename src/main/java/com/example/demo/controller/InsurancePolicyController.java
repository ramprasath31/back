package com.example.demo.controller;

import com.example.demo.*;

import com.example.demo.entity.InsurancePolicy;
import com.example.demo.service.InsurancePolicyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/insurance")
public class InsurancePolicyController {

    @Autowired
    private InsurancePolicyService service;

    @PostMapping("/renew")
    public InsurancePolicy renewPolicy(@RequestBody Map<String, String> request) {
        String policyNumber = request.get("policyNumber");
        String email = request.get("email");
        String insuranceType = request.get("insuranceType");
        LocalDate renewalDate = LocalDate.parse(request.get("renewalDate"));

        return service.renewPolicy(policyNumber, email, insuranceType, renewalDate);
    }

    @GetMapping("/policy/{policyNumber}")
    public InsurancePolicy getPolicy(@PathVariable String policyNumber) {
        return service.getPolicyByNumber(policyNumber);
    }
}
