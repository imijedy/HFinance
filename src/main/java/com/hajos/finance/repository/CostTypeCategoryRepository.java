package com.hajos.finance.repository;


import com.hajos.finance.domain.CostTypeCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CostTypeCategoryRepository extends CrudRepository<CostTypeCategory, Long>{
    List<CostTypeCategory> findAll();
}
