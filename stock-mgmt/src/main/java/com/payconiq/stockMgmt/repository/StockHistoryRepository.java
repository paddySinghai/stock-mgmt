package com.payconiq.stockMgmt.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.payconiq.stockMgmt.dto.model.StockHistory;
import com.payconiq.stockMgmt.exception.StockMgmtBusinessServiceException;

/**
 * Repository to fetch stock pricing History
 * 
 * @author Padmalaya Singhai
 *
 */
@Repository
public interface StockHistoryRepository extends JpaRepository<StockHistory, Long> {

  /**
   * Method to fetch stock pricing history
   * 
   * @param stockId - Long - StockId
   * @return stockDtlsList - List&lt;&gt;StockHistory&lt;&gt;
   * @throws StockMgmtBusinessServiceException
   */
  List<StockHistory> findAllByStockId(@Param("stockId") Long stockId)
      throws StockMgmtBusinessServiceException;

}


