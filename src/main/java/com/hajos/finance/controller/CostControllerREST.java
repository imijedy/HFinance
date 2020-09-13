package com.hajos.finance.controller;

import com.hajos.finance.domain.ChartData;
import com.hajos.finance.domain.Cost;
import com.hajos.finance.domain.CostType;
import com.hajos.finance.domain.CostTypeCategory;
import com.hajos.finance.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CostControllerREST {

    public CostService costService;
    @Autowired
    public void setCostService(CostService costService) {
        this.costService = costService;
    }

    @RequestMapping(path="/getcosts", method= RequestMethod.GET)
    public List<Cost> getCosts(){
        return costService.getAllCosts();
    }

    @RequestMapping(path="/getcost/{id}", method= RequestMethod.GET)
    public Cost getCostById(@PathVariable Long id){
        return costService.getCost(id).get();
    }
    @RequestMapping(path="/deletecost/{id}", method= RequestMethod.GET)
    public void deleteCostById(@PathVariable Long id){
        costService.deleteCost(id);
    }
    @RequestMapping(path="/deletecosttype/{id}", method= RequestMethod.GET)
    public void deleteCostTypeById(@PathVariable Long id){
        costService.deleteCostType(id);
    }

    @RequestMapping(path="/getcosttypes", method= RequestMethod.GET)
    public List<CostType> getCostTypes(){
        return costService.getAllCostTypes();
    }

    @RequestMapping(path="/getcosttypcecategories", method= RequestMethod.GET)
    public List<CostTypeCategory> getCostTypeCategories(){
        return costService.getAllCostTypeCategories();
    }

    @RequestMapping(path="/getcosttype/{id}", method= RequestMethod.GET)
    public CostType getCostTypeById(@PathVariable Long id){
        return costService.getCostType(id).get();
    }
    @RequestMapping(path="/getcosttypecategory/{id}", method= RequestMethod.GET)
    public CostTypeCategory getCostTypeCategoryById(@PathVariable Long id){
        return costService.getCostTypeCategory(id).get();
    }
    @RequestMapping(path="/getcostsbytype/{id}", method= RequestMethod.GET)
    public List<Cost> getCostsByType(@PathVariable Long id){
        return costService.getCostsByType(id);
    }

    @RequestMapping(path="/addcost", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addCost(@RequestBody Cost newCost) {
        costService.addCost(newCost);
    }
    @RequestMapping(path="/addcosttype", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addCostType(@RequestBody CostType newCostType) {
        costService.addCostType(newCostType);
    }

    @RequestMapping(path="/getsumfullyearcost", method= RequestMethod.GET)
    public Double getSumFullYearCost(){
        return costService.getFullYearCostSummary();
    }
    @RequestMapping(path="/getsummonthlycost", method= RequestMethod.GET)
    public Double getSumMonthlyCost(){
        return costService.getMonthlyCostSummary();
    }

    @RequestMapping(path="/getchartcost", method= RequestMethod.GET)
    public List<ChartData> getChartCost(){
        return costService.getChartCost();
    }
}
