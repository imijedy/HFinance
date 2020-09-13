package com.hajos.finance.repository;

import com.hajos.finance.domain.ChartData;
import com.hajos.finance.domain.Income;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IncomeRepository extends CrudRepository<Income, Long> {
    List<Income> findAll();
    @Query(value = "SELECT SUM(AMOUNT) FROM INCOME WHERE YEAR(DATE) = (SELECT YEAR(CURRENT_TIMESTAMP))", nativeQuery = true)
    Double getFullYearIncomeSummary();
    @Query(value = "SELECT SUM(AMOUNT) FROM INCOME WHERE MONTH(DATE) = (SELECT MONTH(CURRENT_TIMESTAMP)) AND YEAR(DATE) = (SELECT YEAR(CURRENT_TIMESTAMP))", nativeQuery = true)
    Double getMonthlyIncomeSummary();

    @Query("SELECT new com.hajos.finance.domain.ChartData(MONTH(i.date), SUM(i.amount)) FROM Income i WHERE YEAR(i.date) = YEAR(CURRENT_TIMESTAMP) GROUP BY MONTH(i.date)")
    List<ChartData> getChartIncome();
}
