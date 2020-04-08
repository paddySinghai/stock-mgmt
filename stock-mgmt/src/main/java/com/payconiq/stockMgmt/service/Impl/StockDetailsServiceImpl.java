/**
 *
 */
package com.payconiq.stockMgmt.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.payconiq.stockMgmt.constants.StockMgmtConstants;
import com.payconiq.stockMgmt.dto.GenericOutputDto;
import com.payconiq.stockMgmt.dto.StockDetailsDto;
import com.payconiq.stockMgmt.dto.model.StockDetails;
import com.payconiq.stockMgmt.dto.model.StockHistory;
import com.payconiq.stockMgmt.exception.StockMgmtBusinessServiceException;
import com.payconiq.stockMgmt.repository.StockHistoryRepository;
import com.payconiq.stockMgmt.repository.StockMgmtRepository;
import com.payconiq.stockMgmt.service.StockDetailsService;

/**
 * Service Implementation of StockDetailsService interface conatining methods for fetching stock
 * details
 *
 * @author Padmalaya Singhai
 *
 */

@Service
public class StockDetailsServiceImpl implements StockDetailsService {

  private StockMgmtRepository stockMgmtRepository;

  private StockHistoryRepository stockHistoryRepository;

  @Autowired
  public StockDetailsServiceImpl(StockMgmtRepository stockMgmtRepository,
      StockHistoryRepository stockHistoryRepository) {
    this.stockMgmtRepository = stockMgmtRepository;
    this.stockHistoryRepository = stockHistoryRepository;

  }

  @Override
  public GenericOutputDto fetchAllDetails() throws StockMgmtBusinessServiceException {
    List<StockDetailsDto> stockList = new ArrayList<>();
    GenericOutputDto outputObj = new GenericOutputDto();

    List<StockDetails> stockDetailsSqlList = stockMgmtRepository.findAll();

    if (stockDetailsSqlList.isEmpty()) {
      throw new StockMgmtBusinessServiceException(StockMgmtConstants.DATABASE_TX_ERROR,
          "No Data Found!");
    }
    for (StockDetails sqlDto : stockDetailsSqlList) {
      stockList.add(StockDetailsDto.builder().id(sqlDto.getId()).name(sqlDto.getName())
          .currentPrice(sqlDto.getCurrentPrice()).lastUpdated(sqlDto.getLastUpdated()).build());
    }
    outputObj.setStockDetailsList(stockList);
    return outputObj;
  }

  @Override
  public GenericOutputDto fetchStockDetails(Long id) throws StockMgmtBusinessServiceException {
    List<StockDetailsDto> stockDetails = new ArrayList<>();
    GenericOutputDto outputObj = new GenericOutputDto();

    Optional<StockDetails> stockDetailsObj = stockMgmtRepository.findById(id);

    stockDetailsObj.orElseThrow(() -> new StockMgmtBusinessServiceException(
        StockMgmtConstants.DATABASE_TX_ERROR, "No Data Found!"));

    stockDetails.add(StockDetailsDto.builder().id(stockDetailsObj.get().getId())
        .name(stockDetailsObj.get().getName()).currentPrice(stockDetailsObj.get().getCurrentPrice())
        .lastUpdated(stockDetailsObj.get().getLastUpdated()).build());

    outputObj.setStockDetailsList(stockDetails);
    return outputObj;
  }

  @Override
  public GenericOutputDto fetchStockUpdationHistory(Long id)
      throws StockMgmtBusinessServiceException {
    GenericOutputDto outputObj = new GenericOutputDto();

    List<StockHistory> stockHistory = stockHistoryRepository.findAllByStockId(id);
    if (stockHistory.isEmpty()) {
      throw new StockMgmtBusinessServiceException(StockMgmtConstants.DATABASE_TX_ERROR,
          "No Data Found!");
    }
    outputObj.setStockHistory(stockHistory);
    return outputObj;
  }

}
