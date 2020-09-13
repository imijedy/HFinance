package com.hajos.finance.service;


import com.hajos.finance.domain.ChartData;
import com.hajos.finance.domain.Income;

import java.util.List;
import java.util.Optional;

public interface IncomeServiceInterface {
    public List<Income> getAllIncome();
    public Optional<Income> getIncome(Long id);
    public void deleteIncome(Long id);
    public void addIncome(Income newIncome);
    public Double getFullYearIncomeSummary();
    public Double getMonthlyIncomeSummary();

    public List<ChartData> getChartIncome();
}
