package com.payconiq.stockMgmt.service;

import com.payconiq.stockMgmt.dto.GenericOutputDto;
import com.payconiq.stockMgmt.dto.model.StockDetails;
import com.payconiq.stockMgmt.exception.StockMgmtBusinessServiceException;

/**
 * Interface class containing methods for creating/updating stock details
 * 
 * @author Padmalaya Singhai
 *
 */
public interface StockMgmtService {

  /**
   * Method to create new Stock
   * 
   * @param stockObj - StockDetails - new Stock Object
   * @return stockDetailsObj - StockDetailsDto - Object of stock details stored in the database
   * @throws StockMgmtBusinessServiceException throws StockMgmBusinessServiceException
   */
  public GenericOutputDto saveStockDetails(StockDetails stockDetailsObj)
      throws StockMgmtBusinessServiceException;

  /**
   * Method to update existing Stock price
   * 
   * @param sqlDto - StockDetails - new Stock Object
   * @return stockDetailsObj - StockDetailsDto - Object of stock details stored in the database
   * @throws StockMgmtBusinessServiceException throws StockMgmBusinessServiceException
   */
  public GenericOutputDto updateStockDetails(StockDetails sqlDto)
      throws StockMgmtBusinessServiceException;


}
