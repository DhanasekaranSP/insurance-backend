package com.insurance.policies.services;

import com.insurance.policies.PolicyRequest;
import com.insurance.policies.entity.PolicyEntity;
import com.insurance.policies.repository.PolicyRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PolicyService {

    @Autowired
    public PolicyRepository policyRepository;


    public static Specification<PolicyEntity> filterPolicies(PolicyRequest request) {
        return (Root<PolicyEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> filters = new ArrayList<>();
            // Filter by Name (if provided)
            if (request.getName() != null && !request.getName().isEmpty()) {
                filters.add(cb.like(cb.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%"));
            }

            // Filter by Min and Max Premium
            if (request.getMinPremium() != null) {
                filters.add(cb.greaterThanOrEqualTo(root.get("premium"), request.getMinPremium()));
            }
            if (request.getMaxPremium() != null) {
                filters.add(cb.lessThanOrEqualTo(root.get("premium"), request.getMaxPremium()));
            }

            // Filter by Types (if provided)
            if (request.getTypes() != null && !request.getTypes().isEmpty()) {
                filters.add(root.get("type").in(request.getTypes()));
            }

            // Filter by Min Coverage (if provided)
            if (request.getMinCoverage() != null) {
                filters.add(cb.greaterThanOrEqualTo(root.get("coverageAmount"), request.getMinCoverage()));
            }

            return cb.and(filters.toArray(new Predicate[0]));
        };
    }

    public List<PolicyEntity> fetchPolicies(PolicyRequest request) {
        Specification<PolicyEntity> spec = filterPolicies(request);
        return policyRepository.findAll(spec);
    }

    public PolicyEntity addPolicy(PolicyEntity policy) {
        return policyRepository.save(policy);
    }

    public PolicyEntity editPolicy(Long id, PolicyEntity updatedPolicy) {
        Optional<PolicyEntity> existingPolicy = policyRepository.findById(id);
        if (existingPolicy.isPresent()) {
            PolicyEntity policy = existingPolicy.get();
            policy.setName(updatedPolicy.getName());
            policy.setPremium(updatedPolicy.getPremium());
            policy.setCoverageAmount(updatedPolicy.getCoverageAmount());
            policy.setType(updatedPolicy.getType());
            return policyRepository.save(policy);
        } else {
            throw new RuntimeException("Policy not found with id: " + id);
        }
    }
    public String deletePolicy(Long id) {
        if (policyRepository.existsById(id)) {
            policyRepository.deleteById(id);
            return "Policy deleted successfully";
        } else {
            return "Policy not found with id: " + id;
        }
    }
}
