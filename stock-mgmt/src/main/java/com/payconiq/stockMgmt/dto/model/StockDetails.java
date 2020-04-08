package com.payconiq.stockMgmt.dto.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "STOCK_DETAILS")
@SequenceGenerator(name="stockSequence")
public class StockDetails {

  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="stockSequence")
  @Column(name = "ID")
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "CURRENT_PRICE", precision = 15, scale = 5)
  private BigDecimal currentPrice;

  @Column(name = "LAST_UPDATED")
  private Timestamp lastUpdated;


}
