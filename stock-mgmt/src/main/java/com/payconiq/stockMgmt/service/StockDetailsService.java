package com.payconiq.stockMgmt.service;

import com.payconiq.stockMgmt.dto.GenericOutputDto;
import com.payconiq.stockMgmt.exception.StockMgmtBusinessServiceException;

/**
 * Service class conatining methods for fetching stock details
 * 
 * @author Padmalaya Singhai
 *
 */
public interface StockDetailsService {

  /**
   * Method to fetch All the stock details on load
   * 
   * @return outputDto - GenericOutputDto - output Dto
   * @throws StockMgmtBusinessServiceException throws StockMgmBusinessServiceException
   */
  public GenericOutputDto fetchAllDetails() throws StockMgmtBusinessServiceException;

  /**
   * Method to fetch Stock details based on Id
   * 
   * @param Id - Long - Stock Id
   * @return outputDto - GenericOutputDto - output Dto
   * @throws StockMgmtBusinessServiceException throws StockMgmBusinessServiceException
   */
  public GenericOutputDto fetchStockDetails(Long id) throws StockMgmtBusinessServiceException;

  /**
   * Method to fetch Stock details Pricing History
   * 
   * @param Id - Long - Stock Id
   * @return outputDto - GenericOutputDto - output Dto
   * @throws StockMgmtBusinessServiceException throws StockMgmBusinessServiceException
   */
  public GenericOutputDto fetchStockUpdationHistory(Long id)
      throws StockMgmtBusinessServiceException;
}
