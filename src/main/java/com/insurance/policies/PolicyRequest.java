package com.insurance.policies;

import java.util.List;

public class PolicyRequest {
    private String name;
    private Double minPremium;
    private Double maxPremium;
    private List<String> types;
    private Double minCoverage;

    public String getName() { return name; }
    public Double getMinPremium() { return minPremium; }
    public Double getMaxPremium() { return maxPremium; }
    public List<String> getTypes() { return types; }
    public Double getMinCoverage() { return minCoverage; }
}
