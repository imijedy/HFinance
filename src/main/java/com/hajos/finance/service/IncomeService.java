package com.hajos.finance.service;

import com.hajos.finance.domain.ChartData;
import com.hajos.finance.domain.Income;
import com.hajos.finance.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeService implements IncomeServiceInterface {

    private IncomeRepository incomeRepository;

    @Autowired
    public void setIncomeRepository(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    @Override
    public List<Income> getAllIncome() {
        return incomeRepository.findAll();
    }

    @Override
    public Optional<Income> getIncome(Long id) {
        return incomeRepository.findById(id);
    }

    @Override
    public void deleteIncome(Long id) {
        incomeRepository.deleteById(id);
    }

    @Override
    public void addIncome(Income newIncome) {
        incomeRepository.save(newIncome);
    }

    @Override
    public Double getFullYearIncomeSummary() {
        return incomeRepository.getFullYearIncomeSummary();
    }

    @Override
    public Double getMonthlyIncomeSummary() {
        return incomeRepository.getMonthlyIncomeSummary();
    }

    @Override
    public List<ChartData> getChartIncome() {
        return incomeRepository.getChartIncome();
    }
}
