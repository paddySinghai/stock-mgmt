package com.payconiq.stockMgmt.dto;

import java.util.List;
import com.payconiq.stockMgmt.dto.model.StockHistory;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericOutputDto {
  @ApiModelProperty(required = false, value = "error Code")
  private String errorCode;
  @ApiModelProperty(required = false, value = "error message")
  private String errorMessage;
  @ApiModelProperty(required = false, value = "message")
  private String message;
  @ApiModelProperty(required = false, value = "Stocks Details List")
  private List<StockDetailsDto> stockDetailsList;
  @ApiModelProperty(required = false, value = "Stocks pricing History")
  private List<StockHistory> stockHistory;
}
