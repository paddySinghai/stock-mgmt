
package com.payconiq.stockMgmt.controller;

import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.payconiq.stockMgmt.dto.GenericOutputDto;
import com.payconiq.stockMgmt.dto.StockDetailsDto;
import com.payconiq.stockMgmt.dto.model.StockDetails;
import com.payconiq.stockMgmt.dto.model.StockHistory;
import com.payconiq.stockMgmt.service.StockDetailsService;

/**
 * Test class Implementation for StockDetailsControlle
 * 
 * @author Padmalaya Singhai
 *
 */
@ExtendWith(MockitoExtension.class)
public class StockDetailsControllerTest {

  @Mock
  private StockDetailsService stockDetailsService;

  @InjectMocks
  private StockDetailsController stockDetailsController;

  private StockDetailsDto stockDetailsDto = new StockDetailsDto();
  private StockDetails stockDetails = new StockDetails();
  private StockHistory stockHistory = new StockHistory();

  private GenericOutputDto inputDto = new GenericOutputDto();


  @BeforeEach
  public void prepareInput() {
    List<StockDetailsDto> stockDetailsList = new ArrayList<>();
    List<StockHistory> stockHistoryList = new ArrayList<>();

    stockDetails = StockDetails.builder().id(1L).name("AbnAmro")
        .currentPrice(BigDecimal.valueOf(2340)).build();

    stockDetailsDto = StockDetailsDto.builder().id(1L).name("AbnAmro")
        .currentPrice(BigDecimal.valueOf(2340)).build();

    stockDetailsList.add(stockDetailsDto);

    stockHistory = StockHistory.builder().name(stockDetailsDto.getName())
        .id(stockDetailsDto.getId()).price(stockDetailsDto.getCurrentPrice()).build();
    stockHistoryList.add(stockHistory);

    inputDto.setStockHistory(stockHistoryList);
    inputDto.setStockDetailsList(stockDetailsList);
  }


  @Test
  public void testFetchAllStocks() {
    Mockito.when(stockDetailsService.fetchAllDetails()).thenReturn(inputDto);
    GenericOutputDto outputDto = stockDetailsController.fetchAllStocks();
    assertTrue(outputDto.getStockDetailsList().get(0) == inputDto.getStockDetailsList().get(0));

  }


  @Test
  public void testFetchStockDetails() {
    Long id = 1l;
    Mockito.when(stockDetailsService.fetchStockDetails(id)).thenReturn(inputDto);
    GenericOutputDto outputEntity = stockDetailsController.fetchStockDetails(id);
    assertTrue(outputEntity.getStockDetailsList().get(0) == inputDto.getStockDetailsList().get(0));

  }

  @Test
  public void testFetchStockUpdationHistory() {
    Long id = 1l;
    Mockito.when(stockDetailsService.fetchStockUpdationHistory(id)).thenReturn(inputDto);
    GenericOutputDto outputEntity = stockDetailsController.fetchStockUpdationHistory(id);
    assertTrue((outputEntity.getStockHistory().get(0) == inputDto.getStockHistory().get(0)));
  }
}

