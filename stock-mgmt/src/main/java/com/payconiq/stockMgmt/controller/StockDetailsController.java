package com.payconiq.stockMgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.payconiq.stockMgmt.dto.GenericOutputDto;
import com.payconiq.stockMgmt.exception.StockMgmtBusinessServiceException;
import com.payconiq.stockMgmt.service.StockDetailsService;
import io.swagger.annotations.ApiOperation;

/**
 * Controller class to view Stock Details
 * 
 * @author Padmalaya Singhai
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class StockDetailsController {

  private StockDetailsService stockDetailsService;

  @Autowired
  public StockDetailsController(StockDetailsService stockDetailsService) {
    this.stockDetailsService = stockDetailsService;
  }

  @ApiOperation(value = "Fetch All Stocks Details")
  @GetMapping(value = "/stocks", headers = "Accept=application/json",
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public GenericOutputDto fetchAllStocks() throws StockMgmtBusinessServiceException {
    return stockDetailsService.fetchAllDetails();

  }

  @ApiOperation(value = "Fetch Stocks Details")
  @GetMapping(value = "/stocks/{id}", headers = "Accept=application/json",
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public GenericOutputDto fetchStockDetails(@PathVariable Long id)
      throws StockMgmtBusinessServiceException {
    return stockDetailsService.fetchStockDetails(id);

  }

  @ApiOperation(value = "Fetch Stock Pricing History")
  @GetMapping(value = "/stocks/{id}/history", headers = "Accept=application/json",
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public GenericOutputDto fetchStockUpdationHistory(@PathVariable Long id)
      throws StockMgmtBusinessServiceException {
    return stockDetailsService.fetchStockUpdationHistory(id);

  }
}
