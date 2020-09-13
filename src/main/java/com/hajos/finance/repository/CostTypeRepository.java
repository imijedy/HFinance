package com.hajos.finance.repository;

import com.hajos.finance.domain.CostType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CostTypeRepository extends CrudRepository<CostType, Long> {
    List<CostType> findAll();
}
