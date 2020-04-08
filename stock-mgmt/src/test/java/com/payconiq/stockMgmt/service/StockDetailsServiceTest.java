
package com.payconiq.stockMgmt.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.payconiq.stockMgmt.dto.GenericOutputDto;
import com.payconiq.stockMgmt.dto.model.StockDetails;
import com.payconiq.stockMgmt.dto.model.StockHistory;
import com.payconiq.stockMgmt.exception.StockMgmtBusinessServiceException;
import com.payconiq.stockMgmt.repository.StockHistoryRepository;
import com.payconiq.stockMgmt.repository.StockMgmtRepository;
import com.payconiq.stockMgmt.service.Impl.StockDetailsServiceImpl;

/**
 * Test Implementation for StockDetailsServiceImpl
 * 
 * @author Padmalaya Singhai
 *
 */
@ExtendWith(MockitoExtension.class)

public class StockDetailsServiceTest {

  @InjectMocks
  private StockDetailsServiceImpl stockDetailsService;

  @Mock
  private StockMgmtRepository stockMgmtRepository;

  @Mock
  private StockHistoryRepository stockHistoryRepository;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testFetchAllDetails() {
    List<StockDetails> list = new ArrayList<>();
    StockDetails obj1 =
        StockDetails.builder().id(1L).name("ING").currentPrice(BigDecimal.valueOf(2350)).build();
    StockDetails obj2 =
        StockDetails.builder().id(2L).name("Rabo Bank").currentPrice(BigDecimal.valueOf(2550)).build();
    StockDetails obj3 = StockDetails.builder().id(2L).name("Payconiq_Marqt")
        .currentPrice(BigDecimal.valueOf(2750)).build();
    list.add(obj1);
    list.add(obj2);
    list.add(obj3);
    Mockito.when(stockMgmtRepository.findAll()).thenReturn(list);
    GenericOutputDto dto = stockDetailsService.fetchAllDetails();
    assertNotNull(dto.getStockDetailsList());
  }

  @Test
  public void testFetchAllDetailsValidation() throws StockMgmtBusinessServiceException {

    List<StockDetails> list = new ArrayList<>();
    Mockito.when(stockMgmtRepository.findAll()).thenReturn(list);
    Assertions.assertThrows(StockMgmtBusinessServiceException.class, () -> {
      stockDetailsService.fetchAllDetails();
    });

  }

  @Test
  public void fetchStockDetails() {
    StockDetails obj1 =
        StockDetails.builder().id(1L).name("ING").currentPrice(BigDecimal.valueOf(2350)).build();
    Mockito.when(stockMgmtRepository.findById(1L)).thenReturn(Optional.of(obj1));
    GenericOutputDto dto = stockDetailsService.fetchStockDetails(1L);
    assertNotNull(dto.getStockDetailsList());
  }

  @Test
  public void fetchStockDetailsValidation() {
    StockDetails obj1 = new StockDetails();
    Mockito.when(stockMgmtRepository.findById(1L)).thenReturn(Optional.of(obj1));
    GenericOutputDto dto = stockDetailsService.fetchStockDetails(1L);
    assertTrue(null == dto.getStockDetailsList().get(0).getId());
  }


  @Test
  public void fetchStockUpdationHistory() {
    StockDetails obj = StockDetails.builder().id(1l).name("ING").currentPrice(BigDecimal.valueOf(123))
        .lastUpdated(new Timestamp((new java.sql.Date(System.currentTimeMillis())).getTime()))
        .build();
    List<StockHistory> list = new ArrayList<>();
    StockHistory obj1 =
        StockHistory.builder().id(1L).name("ING").price(BigDecimal.valueOf(2350)).stock(obj).build();
    StockHistory obj2 =
        StockHistory.builder().id(2L).name("ING").price(BigDecimal.valueOf(2550)).stock(obj).build();
    StockHistory obj3 =
        StockHistory.builder().id(2L).name("ING").price(BigDecimal.valueOf(2750)).stock(obj).build();
    list.add(obj1);
    list.add(obj2);
    list.add(obj3);
    Mockito.when(stockHistoryRepository.findAllByStockId(1L)).thenReturn(list);
    GenericOutputDto dto = stockDetailsService.fetchStockUpdationHistory(1L);
    assertNotNull(dto.getStockHistory());
  }

  @Test
  public void fetchStockUpdationHistoryValidation() throws StockMgmtBusinessServiceException {

    List<StockHistory> list = new ArrayList<>();
    Mockito.when(stockHistoryRepository.findAllByStockId(10L)).thenReturn(list);
    Assertions.assertThrows(StockMgmtBusinessServiceException.class, () -> {
      stockDetailsService.fetchStockUpdationHistory(10L);
    });
  }
}

