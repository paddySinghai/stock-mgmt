package com.payconiq.stockMgmt.util;

import java.math.BigDecimal;

/**
 * Utility class containing general functions used across the application
 * 
 * @author Padmalaya Singhai
 *
 */
public final class CommonUtil {
  /**
   * returns true Only if the provided BigDecimal value is not null and not zero
   * 
   * @param value - BigDecimal - input value
   * @return Boolean
   */
  public static boolean isNotNullAndNotZero(BigDecimal value) {
    return null != value && BigDecimal.ZERO.compareTo(value) != 0;
  }

  /**
   * returns true Only if the provided Long value is not null and not zero
   * 
   * @param value - Long - input value
   * @return Boolean
   */
  public static boolean isValidNumber(Long value) {
    return null != value && value != 0.0;
  }
}
