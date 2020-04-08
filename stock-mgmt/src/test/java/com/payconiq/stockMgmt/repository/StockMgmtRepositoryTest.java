package com.payconiq.stockMgmt.repository;

import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import com.payconiq.stockMgmt.dto.model.StockDetails;
import com.payconiq.stockMgmt.exception.StockMgmtBusinessServiceException;

/**
 * Test Implementation for StockMgmtRepository
 * 
 * @author Padmalaya Singhai
 *
 */
@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class StockMgmtRepositoryTest {

  @Autowired
  private StockMgmtRepository stockMgmtRepository;
  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void testSave() throws StockMgmtBusinessServiceException {
    StockDetails obj1 = StockDetails.builder().id(4L).name("ING").currentPrice(BigDecimal.valueOf(2350))
        .lastUpdated(new Timestamp((new java.sql.Date(System.currentTimeMillis())).getTime()))
        .build();
    entityManager.merge(obj1);
    entityManager.flush();
    StockDetails output = stockMgmtRepository.save(obj1);
    assertTrue(output.getName().equals(obj1.getName()));

  }

  @Test
  public void testFindById() throws StockMgmtBusinessServiceException {
    StockDetails obj1 = StockDetails.builder().id(1L).name("ING").currentPrice(BigDecimal.valueOf(2350))
        .lastUpdated(new Timestamp((new java.sql.Date(System.currentTimeMillis())).getTime()))
        .build();
    entityManager.merge(obj1);
    entityManager.flush();
    Optional<StockDetails> output = stockMgmtRepository.findById(obj1.getId());
    assertTrue(output.get().getName().equals(obj1.getName()));

  }

  @Test
  public void testFindAll() throws StockMgmtBusinessServiceException {
    StockDetails obj1 = StockDetails.builder().id(1L).name("ING").currentPrice(BigDecimal.valueOf(2350))
        .lastUpdated(new Timestamp((new java.sql.Date(System.currentTimeMillis())).getTime()))
        .build();
    StockDetails obj2 =
        StockDetails.builder().id(2L).name("Rabo").currentPrice(BigDecimal.valueOf(2350))
            .lastUpdated(new Timestamp((new java.sql.Date(System.currentTimeMillis())).getTime()))
            .build();
    StockDetails obj3 =
        StockDetails.builder().id(3L).name("Abn Amro").currentPrice(BigDecimal.valueOf(2350))
            .lastUpdated(new Timestamp((new java.sql.Date(System.currentTimeMillis())).getTime()))
            .build();
    entityManager.merge(obj1);
    entityManager.merge(obj2);
    entityManager.merge(obj3);
    entityManager.flush();
    List<StockDetails> output = stockMgmtRepository.findAll();
    assertTrue(output.get(0).getName().equals(obj1.getName()));

  }
}
