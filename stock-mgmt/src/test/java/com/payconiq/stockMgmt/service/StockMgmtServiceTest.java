
package com.payconiq.stockMgmt.service;

import static org.junit.Assert.assertNotNull;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.payconiq.stockMgmt.dto.GenericOutputDto;
import com.payconiq.stockMgmt.dto.model.StockDetails;
import com.payconiq.stockMgmt.dto.model.StockHistory;
import com.payconiq.stockMgmt.repository.StockHistoryRepository;
import com.payconiq.stockMgmt.repository.StockMgmtRepository;
import com.payconiq.stockMgmt.service.Impl.StockMgmtServiceImpl;

/**
 * Test Implementation for StockMgmtServiceImpl
 * 
 * @author Padmalaya Singhai
 *
 */
@ExtendWith(MockitoExtension.class)
public class StockMgmtServiceTest {

  @InjectMocks
  private StockMgmtServiceImpl stockMgmtService;

  @Mock
  private StockMgmtRepository stockMgmtRepository;

  @Mock(lenient = true)
  private StockHistoryRepository stockHistoryRepository;


  @Test
  public void testSaveStockDetails() {
    StockDetails obj1 =
        StockDetails.builder().id(1L).name("ING").currentPrice(BigDecimal.valueOf(2350)).build();

    StockHistory historyObj1 = StockHistory.builder().name(obj1.getName())
        .price(obj1.getCurrentPrice()).stock(obj1).updationDate(obj1.getLastUpdated()).build();

    Mockito.when(stockMgmtRepository.save(obj1)).thenReturn(obj1);

    Mockito.when(stockHistoryRepository.save(historyObj1)).thenReturn(historyObj1);

    GenericOutputDto dto = stockMgmtService.saveStockDetails(obj1);

    assertNotNull(dto.getMessage());
  }

  @Test
  public void testUpdateStockDetails() {
    StockDetails obj1 =
        StockDetails.builder().id(1L).name("ING").currentPrice(BigDecimal.valueOf(2350)).build();

    StockHistory historyObj1 = StockHistory.builder().name(obj1.getName())
        .price(obj1.getCurrentPrice()).stock(obj1).updationDate(obj1.getLastUpdated()).build();

    Mockito.when(stockMgmtRepository.findById(obj1.getId())).thenReturn(Optional.of(obj1));

    Mockito.when(stockMgmtRepository.save(obj1)).thenReturn(obj1);

    Mockito.when(stockHistoryRepository.save(historyObj1)).thenReturn(historyObj1);

    GenericOutputDto dto = stockMgmtService.updateStockDetails(obj1);
    assertNotNull(dto.getMessage());
  }

}

