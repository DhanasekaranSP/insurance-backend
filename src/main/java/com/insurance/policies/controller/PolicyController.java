package com.insurance.policies.controller;

import com.insurance.policies.PolicyRequest;
import com.insurance.policies.entity.PolicyEntity;
import com.insurance.policies.services.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PolicyController {

    @Autowired
    public PolicyService policyService;

    @GetMapping("/fetch")
    public void fetch(){
        System.out.println("Hello");
    }

    @PostMapping("/insurance")
    public List<PolicyEntity> fetchPolicies(@RequestBody PolicyRequest request) {
        return policyService.fetchPolicies(request);
    }

    @PostMapping("/add")
    public PolicyEntity addPolicy(@RequestBody PolicyEntity policy) {
        return policyService.addPolicy(policy);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePolicy(@PathVariable Long id) {
        return policyService.deletePolicy(id);
    }

    @PutMapping("/edit/{id}")
    public PolicyEntity editPolicy(@PathVariable Long id, @RequestBody PolicyEntity policy) {
        return policyService.editPolicy(id, policy);
    }

}
