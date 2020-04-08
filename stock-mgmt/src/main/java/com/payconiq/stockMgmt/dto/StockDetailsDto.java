package com.payconiq.stockMgmt.dto;

import java.math.BigDecimal;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockDetailsDto {
  @ApiModelProperty(required = true,
      value = "Stock Id(Mandatory only in case of updation of details)")
  private Long id;
  @ApiModelProperty(required = true, value = "Stock Name")
  private String name;
  @ApiModelProperty(required = true, value = "Current Price of the Stock")
  private BigDecimal currentPrice;
  @ApiModelProperty(required = true, value = "Updation Date")
  private Date lastUpdated;

}
