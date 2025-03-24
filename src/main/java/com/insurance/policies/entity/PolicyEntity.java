package com.insurance.policies.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "policies")
public class PolicyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private int premium;
    private int coverageAmount;

    public PolicyEntity() {}

    public PolicyEntity(String name, String type, int premium, int coverageAmount) {
        this.name = name;
        this.type = type;
        this.premium = premium;
        this.coverageAmount = coverageAmount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPremium(int premium) {
        this.premium = premium;
    }

    public void setCoverageAmount(int coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public int getPremium() { return premium; }
    public int getCoverageAmount() { return coverageAmount; }
}

