package com.payconiq.stockMgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payconiq.stockMgmt.dto.model.StockDetails;

/**
 * Repository to create/update/view stock details
 * 
 * @author Padmalaya Singhai
 *
 */
@Repository
public interface StockMgmtRepository extends JpaRepository<StockDetails, Long> {

}
