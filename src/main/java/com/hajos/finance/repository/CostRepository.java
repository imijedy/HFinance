package com.hajos.finance.repository;


import com.hajos.finance.domain.ChartData;
import com.hajos.finance.domain.Cost;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface CostRepository extends CrudRepository<Cost, Long> {
    List<Cost> findAll();
    List<Cost> findBypaymentDeadlineBetween(LocalDate start, LocalDate end);
    List<Cost> findByCostTypeId(Long id);

    @Query(value = "SELECT SUM(AMOUNT) FROM COST WHERE YEAR(PAYMENT_PERIOD) = (SELECT YEAR(CURRENT_TIMESTAMP))", nativeQuery = true)
    Double getFullYearCostSummary();
    //@Query("SELECT new com.hajos.finance.domain.ChartData(MONTH(c.paymentPeriod), SUM(c.amount)) FROM Cost c GROUP BY MONTH(c.paymentPeriod) HAVING YEAR(c.paymentPeriod) = YEAR(CURRENT_TIMESTAMP)")
    @Query("SELECT new com.hajos.finance.domain.ChartData(MONTH(c.paymentPeriod), SUM(c.amount)) FROM Cost c WHERE YEAR(c.paymentPeriod) = YEAR(CURRENT_TIMESTAMP) GROUP BY MONTH(c.paymentPeriod)")
    List<ChartData> getChartCost();
    @Query(value = "SELECT SUM(AMOUNT) FROM COST WHERE MONTH(PAYMENT_PERIOD) = (SELECT MONTH(CURRENT_TIMESTAMP)) AND YEAR(PAYMENT_PERIOD) = (SELECT YEAR(CURRENT_TIMESTAMP))", nativeQuery = true)
    Double getMonthlyCostSummary();

}
