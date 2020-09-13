package com.hajos.finance.service;

import com.hajos.finance.domain.ChartData;
import com.hajos.finance.domain.Cost;
import com.hajos.finance.domain.CostType;
import com.hajos.finance.domain.CostTypeCategory;
import com.hajos.finance.repository.CostRepository;
import com.hajos.finance.repository.CostTypeCategoryRepository;
import com.hajos.finance.repository.CostTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CostService implements CostServiceInterface{

    private CostRepository costRepository;
    private CostTypeCategoryRepository costTypeCategoryRepository;
    private CostTypeRepository costTypeRepository;

    @Autowired
    public void setCostTypeCategoryRepository(CostTypeCategoryRepository costTypeCategoryRepository) {
        this.costTypeCategoryRepository = costTypeCategoryRepository;
    }
    @Autowired
    public void setCostRepository(CostRepository costRepository) {
        this.costRepository = costRepository;
    }
    @Autowired
    public void setCostTypeRepository(CostTypeRepository costTypeRepository) {
        this.costTypeRepository = costTypeRepository;
    }

    public List<Cost> getAllCosts(){
        return costRepository.findAll();
    }

    @Override
    public List<CostType> getAllCostTypes() {
        return costTypeRepository.findAll();
    }

    @Override
    public List<CostTypeCategory> getAllCostTypeCategories() {
        return costTypeCategoryRepository.findAll();
    }

    @Override
    public Optional<CostType> getCostType(Long id) {
        return costTypeRepository.findById(id);
    }

    @Override
    public Optional<CostTypeCategory> getCostTypeCategory(Long id) {
        return costTypeCategoryRepository.findById(id);
    }

    public List<Cost> getAllMonthlyCosts(){
        LocalDate start = LocalDate.ofEpochDay(System.currentTimeMillis() / (24 * 60 * 60 * 1000) ).withDayOfMonth(1);
        LocalDate end = LocalDate.ofEpochDay(System.currentTimeMillis() / (24 * 60 * 60 * 1000) ).plusMonths(1).withDayOfMonth(1).minusDays(1);
        return costRepository.findBypaymentDeadlineBetween(start,end);
    }
    public List<Cost> getAllMonthlyPaidCosts(){
        return null;
    }
    public List<Cost> getAllPrevousCosts(){
        return null;
    }
    public Optional<Cost> getCost(Long id){
        return costRepository.findById(id);
    }
    public void deleteCost(Long id){
        costRepository.deleteById(id);
    }

    @Override
    public void deleteCostType(Long id) {
        costTypeRepository.deleteById(id);
    }

    public void updateCost(Cost editedCost){
        costRepository.save(editedCost);
    }

    @Override
    public void addCost(Cost newCost) {
        costRepository.save(newCost);
    }

    @Override
    public void addCostType(CostType newCostType) {
        costTypeRepository.save(newCostType);
    }

    @Override
    public List<Cost> getCostsByType(Long id) {
        return costRepository.findByCostTypeId(id);
    }

    @Override
    public Double getFullYearCostSummary() {
        return costRepository.getFullYearCostSummary();
    }

    @Override
    public Double getMonthlyCostSummary() {
        return costRepository.getMonthlyCostSummary();
    }

    @Override
    public List<ChartData> getChartCost() {
        return costRepository.getChartCost();
    }
}
