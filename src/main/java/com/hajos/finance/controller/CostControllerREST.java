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

    @CrossOrigin(origins = "*")
    @RequestMapping(path="/getcosts", method= RequestMethod.GET)
    public List<Cost> getCosts(){
        return costService.getAllCosts();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path="/getcost/{id}", method= RequestMethod.GET)
    public Cost getCostById(@PathVariable Long id){
        return costService.getCost(id).get();
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(path="/deletecost/{id}", method= RequestMethod.GET)
    public void deleteCostById(@PathVariable Long id){
        costService.deleteCost(id);
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(path="/deletecosttype/{id}", method= RequestMethod.GET)
    public void deleteCostTypeById(@PathVariable Long id){
        costService.deleteCostType(id);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path="/getcosttypes", method= RequestMethod.GET)
    public List<CostType> getCostTypes(){
        return costService.getAllCostTypes();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path="/getcosttypcecategories", method= RequestMethod.GET)
    public List<CostTypeCategory> getCostTypeCategories(){
        return costService.getAllCostTypeCategories();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path="/getcosttype/{id}", method= RequestMethod.GET)
    public CostType getCostTypeById(@PathVariable Long id){
        return costService.getCostType(id).get();
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(path="/getcosttypecategory/{id}", method= RequestMethod.GET)
    public CostTypeCategory getCostTypeCategoryById(@PathVariable Long id){
        return costService.getCostTypeCategory(id).get();
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(path="/getcostsbytype/{id}", method= RequestMethod.GET)
    public List<Cost> getCostsByType(@PathVariable Long id){
        return costService.getCostsByType(id);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path="/addcost", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addCost(@RequestBody Cost newCost) {
        costService.addCost(newCost);
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(path="/addcosttype", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addCostType(@RequestBody CostType newCostType) {
        costService.addCostType(newCostType);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path="/getsumfullyearcost", method= RequestMethod.GET)
    public Double getSumFullYearCost(){
        return costService.getFullYearCostSummary();
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(path="/getsummonthlycost", method= RequestMethod.GET)
    public Double getSumMonthlyCost(){
        return costService.getMonthlyCostSummary();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path="/getchartcost", method= RequestMethod.GET)
    public List<ChartData> getChartCost(){
        return costService.getChartCost();
    }
}
