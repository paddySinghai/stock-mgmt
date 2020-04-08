package com.payconiq.stockMgmt.service.Impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.Instant;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.payconiq.stockMgmt.constants.StockMgmtConstants;
import com.payconiq.stockMgmt.dto.GenericOutputDto;
import com.payconiq.stockMgmt.dto.model.StockDetails;
import com.payconiq.stockMgmt.dto.model.StockHistory;
import com.payconiq.stockMgmt.exception.StockMgmtBusinessServiceException;
import com.payconiq.stockMgmt.repository.StockHistoryRepository;
import com.payconiq.stockMgmt.repository.StockMgmtRepository;
import com.payconiq.stockMgmt.service.StockMgmtService;
import com.payconiq.stockMgmt.util.HelperUtil;

/**
 * Service Implementation for StockMgmtService containing methods for creating/updating stock
 * details
 * 
 * @author Padmalaya Singhai
 *
 */
@Service
public class StockMgmtServiceImpl implements StockMgmtService {

  private StockMgmtRepository stockMgmtRepository;

  private StockHistoryRepository stockHistoryRepository;

  @Autowired
  public StockMgmtServiceImpl(StockMgmtRepository stockMgmtRepository,
      StockHistoryRepository stockHistoryRepository) {
    this.stockMgmtRepository = stockMgmtRepository;
    this.stockHistoryRepository = stockHistoryRepository;
  }


  @Override
  public GenericOutputDto saveStockDetails(StockDetails stockDetailsObj)
      throws StockMgmtBusinessServiceException {

    GenericOutputDto output = new GenericOutputDto();

    stockDetailsObj.setLastUpdated(Timestamp.from(Instant.now(Clock.systemDefaultZone())));

    stockDetailsObj = stockMgmtRepository.save(stockDetailsObj);

    output.setMessage("Stock details saved Successfully!");
    return output;
  }

  @Override
  public GenericOutputDto updateStockDetails(StockDetails stockDetailsObj)
      throws StockMgmtBusinessServiceException {
    GenericOutputDto output = new GenericOutputDto();


    Optional<StockDetails> currentObj = stockMgmtRepository.findById(stockDetailsObj.getId());

    currentObj.orElseThrow(() -> new StockMgmtBusinessServiceException(
        StockMgmtConstants.DATABASE_TX_ERROR, "No Matching Details Found!"));

    currentObj.get().setCurrentPrice(stockDetailsObj.getCurrentPrice());
    currentObj.get().setLastUpdated(Timestamp.from(Instant.now(Clock.systemDefaultZone())));
    stockDetailsObj = stockMgmtRepository.save(currentObj.get());

    stockHistoryRepository.save(HelperUtil.prepareInputForSqlHistory(stockDetailsObj));

    output.setMessage("Stock details updated Successfully!");
    return output;
  }
}


