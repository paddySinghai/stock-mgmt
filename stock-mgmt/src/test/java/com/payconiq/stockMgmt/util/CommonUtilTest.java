package com.payconiq.stockMgmt.util;

import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Test Implementation for CommonUtil,java
 * 
 * @author Padmalaya Singhai
 *
 */
@ExtendWith(MockitoExtension.class)
public class CommonUtilTest {
  @Test
  public void testIsNotNullAndNotZero() {
    assertTrue(CommonUtil.isNotNullAndNotZero(BigDecimal.valueOf(23450)));
  }

  @Test
  public void testIsValidNumber() {
    assertTrue(CommonUtil.isValidNumber(23l));
  }
}
