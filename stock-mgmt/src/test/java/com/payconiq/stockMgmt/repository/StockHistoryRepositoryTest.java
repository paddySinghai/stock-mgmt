
package com.payconiq.stockMgmt.repository;

import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import com.payconiq.stockMgmt.dto.model.StockDetails;
import com.payconiq.stockMgmt.dto.model.StockHistory;
import com.payconiq.stockMgmt.exception.StockMgmtBusinessServiceException;

/**
 * Test Implementation for StockHistoryRepository
 * 
 * @author Padmalaya Singhai
 *
 */
@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class StockHistoryRepositoryTest {

  @Autowired
  private StockHistoryRepository stockHistoryRepository;

  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void testFindAllByStockId() throws StockMgmtBusinessServiceException {
    StockDetails obj = StockDetails.builder().id(1l).name("ING").currentPrice(BigDecimal.valueOf(123))
        .lastUpdated(new Timestamp((new java.sql.Date(System.currentTimeMillis())).getTime()))
        .build();
    List<StockHistory> list = new ArrayList<>();
    StockHistory obj1 =
        StockHistory.builder().id(1L).name("ING").price(BigDecimal.valueOf(2350)).stock(obj)
            .updationDate(new Timestamp((new java.sql.Date(System.currentTimeMillis())).getTime()))
            .build();
    StockHistory obj2 =
        StockHistory.builder().id(2L).name("ING").price(BigDecimal.valueOf(2550)).stock(obj)
            .updationDate(new Timestamp((new java.sql.Date(System.currentTimeMillis())).getTime()))
            .build();
    StockHistory obj3 =
        StockHistory.builder().id(2L).name("ING").price(BigDecimal.valueOf(2750)).stock(obj)
            .updationDate(new Timestamp((new java.sql.Date(System.currentTimeMillis())).getTime()))
            .build();
    list.add(obj1);
    list.add(obj2);
    list.add(obj3);
    entityManager.merge(obj1);
    entityManager.merge(obj2);
    entityManager.merge(obj3);
    entityManager.flush();
    List<StockHistory> outputList = stockHistoryRepository.findAllByStockId(1L);
    assertTrue(outputList.get(0).getName().equals(list.get(0).getName()));

  }

  @Test
  public void testSave() throws StockMgmtBusinessServiceException {
    StockDetails obj = StockDetails.builder().id(1l).name("ING").currentPrice(BigDecimal.valueOf(123))
        .lastUpdated(new Timestamp((new java.sql.Date(System.currentTimeMillis())).getTime()))
        .build();
    StockHistory obj1 =
        StockHistory.builder().id(4L).name("ING").price(BigDecimal.valueOf(2350)).stock(obj)
            .updationDate(new Timestamp((new java.sql.Date(System.currentTimeMillis())).getTime()))
            .build();
    entityManager.merge(obj1);
    entityManager.flush();
    StockHistory output = stockHistoryRepository.save(obj1);
    assertTrue(output.getName().equals(obj1.getName()));

  }

}

