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

    @CrossOrigin(origins = "*")
    @RequestMapping(path="/getincome/{id}", method= RequestMethod.GET)
    public Income getIncomeById(@PathVariable Long id){
        return incomeService.getIncome(id).get();
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(path="/getincome", method= RequestMethod.GET)
    public List<Income> getIncome(){
        return incomeService.getAllIncome();
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(path="/getsumfullyearincome", method= RequestMethod.GET)
    public Double getSumFullYearIncome(){
        return incomeService.getFullYearIncomeSummary();
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(path="/getsummonthlyincome", method= RequestMethod.GET)
    public Double getSumMonthlyIncome(){
        return incomeService.getMonthlyIncomeSummary();
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(path="/deleteincome/{id}", method= RequestMethod.GET)
    public void deleteIncomeById(@PathVariable Long id){
        incomeService.deleteIncome(id);
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(path="/addincome", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addIncome(@RequestBody Income newIncome) {
        incomeService.addIncome(newIncome);
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(path="/getchartincome", method= RequestMethod.GET)
    public List<ChartData> getChartIncome(){
        return incomeService.getChartIncome();
    }
}
