package com.payconiq.stockMgmt.util;

import java.util.List;
import org.springframework.validation.FieldError;
import com.payconiq.stockMgmt.dto.StockDetailsDto;
import com.payconiq.stockMgmt.dto.model.StockDetails;
import com.payconiq.stockMgmt.dto.model.StockHistory;

public class HelperUtil {
  public static StockDetails prepareSqlInput(StockDetailsDto stockDetailsDto) {
    StockDetails sqlDto = StockDetails.builder().id(stockDetailsDto.getId())
        .currentPrice(stockDetailsDto.getCurrentPrice()).name(stockDetailsDto.getName()).build();
    return sqlDto;
  }

  public static StockDetails prepareSqlInputForUpdate(StockDetailsDto stockDetailsDto) {
    StockDetails sqlDto = StockDetails.builder().id(stockDetailsDto.getId())
        .currentPrice(stockDetailsDto.getCurrentPrice()).build();
    return sqlDto;
  }

  public static StockHistory prepareInputForSqlHistory(StockDetails stockDetailsDto) {
    StockHistory sqlDto = StockHistory.builder().price(stockDetailsDto.getCurrentPrice())
        .updationDate(stockDetailsDto.getLastUpdated()).stock(stockDetailsDto)
        .name(stockDetailsDto.getName()).build();
    return sqlDto;
  }

  public static String prepareErrorMessages(List<FieldError> listError) {
    StringBuilder errorMsg = new StringBuilder();
    for (FieldError error : listError) {
      errorMsg.append("Filed-->" + error.getField() + "," + "Error-->" + error.getCode() + ","
          + "Value-->" + error.getRejectedValue() + System.getProperty("line.separator"));
    }
    return errorMsg.toString();

  }
}
