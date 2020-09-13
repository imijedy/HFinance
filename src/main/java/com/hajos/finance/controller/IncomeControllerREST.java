package com.hajos.finance.controller;

import com.hajos.finance.domain.ChartData;
import com.hajos.finance.domain.Income;
import com.hajos.finance.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IncomeControllerREST {

    private IncomeService incomeService;

    @Autowired
    public void setIncomeService(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @RequestMapping(path="/getincome/{id}", method= RequestMethod.GET)
    public Income getIncomeById(@PathVariable Long id){
        return incomeService.getIncome(id).get();
    }
    @RequestMapping(path="/getincome", method= RequestMethod.GET)
    public List<Income> getIncome(){
        return incomeService.getAllIncome();
    }
    @RequestMapping(path="/getsumfullyearincome", method= RequestMethod.GET)
    public Double getSumFullYearIncome(){
        return incomeService.getFullYearIncomeSummary();
    }
    @RequestMapping(path="/getsummonthlyincome", method= RequestMethod.GET)
    public Double getSumMonthlyIncome(){
        return incomeService.getMonthlyIncomeSummary();
    }
    @RequestMapping(path="/deleteincome/{id}", method= RequestMethod.GET)
    public void deleteIncomeById(@PathVariable Long id){
        incomeService.deleteIncome(id);
    }
    @RequestMapping(path="/addincome", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addIncome(@RequestBody Income newIncome) {
        incomeService.addIncome(newIncome);
    }

    @RequestMapping(path="/getchartincome", method= RequestMethod.GET)
    public List<ChartData> getChartIncome(){
        return incomeService.getChartIncome();
    }
}
