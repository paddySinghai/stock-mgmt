package com.payconiq.stockMgmt.dto.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "STOCK_HISTORY")
public class StockHistory {
  @Id
  @GeneratedValue
  @Column(name = "ID")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "STOCK_ID")
  private StockDetails stock;

  @Column(name = "NAME")
  private String name;

  @Column(name = "PRICE", precision = 15, scale = 5)
  private BigDecimal price;

  @Column(name = "UPDATED_ON")
  private Timestamp updationDate;
}
