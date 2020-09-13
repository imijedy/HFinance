package com.hajos.finance.service;

import com.hajos.finance.domain.ChartData;
import com.hajos.finance.domain.Cost;
import com.hajos.finance.domain.CostType;
import com.hajos.finance.domain.CostTypeCategory;

import java.util.List;
import java.util.Optional;

public interface CostServiceInterface {
    public List<Cost> getAllCosts();
    public List<CostType> getAllCostTypes();
    public List<CostTypeCategory> getAllCostTypeCategories();
    public Optional<CostType> getCostType(Long id);
    public Optional<CostTypeCategory> getCostTypeCategory(Long id);
    public List<Cost> getAllMonthlyCosts();
    public List<Cost> getAllMonthlyPaidCosts();
    public List<Cost> getAllPrevousCosts();
    public Optional<Cost> getCost(Long id);
    public void deleteCost(Long id);
    public void deleteCostType(Long id);
    public void updateCost(Cost editedCost);
    public void addCost(Cost newCost);
    public void addCostType(CostType newCostType);
    public List<Cost> getCostsByType(Long id);

    public Double getFullYearCostSummary();
    public Double getMonthlyCostSummary();
    public List<ChartData> getChartCost();
}
