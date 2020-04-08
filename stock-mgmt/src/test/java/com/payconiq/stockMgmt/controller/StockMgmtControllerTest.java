package com.payconiq.stockMgmt.controller;

import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import com.payconiq.stockMgmt.constants.StockMgmtConstants;
import com.payconiq.stockMgmt.dto.GenericOutputDto;
import com.payconiq.stockMgmt.dto.StockDetailsDto;
import com.payconiq.stockMgmt.dto.model.StockDetails;
import com.payconiq.stockMgmt.service.StockMgmtService;
import com.payconiq.stockMgmt.validator.StockMgmtValidator;

/**
 * Test class Implementation for StockMgmtController
 * 
 * @author Padmalaya Singhai
 *
 */
@ExtendWith(MockitoExtension.class)
public class StockMgmtControllerTest {

  @InjectMocks
  private StockMgmtController stockMgmtController;

  @Mock
  private StockMgmtService stockMgmtService;

  @Mock
  private BindingResult result;

  private StockDetailsDto stockDetailsDto = new StockDetailsDto();
  private StockDetails stockDetails = new StockDetails();
  private GenericOutputDto input = new GenericOutputDto();


  @InitBinder
  public void initBinder(WebDataBinder dataBinder) {
    dataBinder.setValidator(new StockMgmtValidator());
  }

  @BeforeEach
  public void prepareInput() {
    stockDetails =
        StockDetails.builder().id(1L).name("Abn Amro").currentPrice(BigDecimal.valueOf(2340)).build();
    stockDetailsDto = StockDetailsDto.builder().id(1L).name("Abn Amro")
        .currentPrice(BigDecimal.valueOf(2340)).build();
  }

  @Test
  public void testCreateNewStock() {
    input.setMessage("Stock details saved Successfully!");
    Mockito.when(result.hasErrors()).thenReturn(false);
    Mockito.when(stockMgmtService.saveStockDetails(stockDetails)).thenReturn(input);
    GenericOutputDto outputDto = stockMgmtController.createNewStock(stockDetailsDto, result);
    assertTrue(null != outputDto.getMessage() && outputDto.getMessage().equals(input.getMessage()));

  }

  @Test
  public void testCreateNewStockValidation() {
    input.setErrorCode(StockMgmtConstants.DATA_VALIDATION_ERROR);
    Mockito.when(result.hasErrors()).thenReturn(true);
    GenericOutputDto outputDto = stockMgmtController.createNewStock(stockDetailsDto, result);
    assertTrue(null != outputDto.getErrorCode()
        && StockMgmtConstants.DATA_VALIDATION_ERROR.equals(outputDto.getErrorCode()));

  }

  @Test
  public void testUpdateStockDetails() {
    input.setMessage("Stock details updated Successfully!");
    stockDetails.setName(null);
    Mockito.when(result.hasErrors()).thenReturn(false);
    Mockito.when(stockMgmtService.updateStockDetails(stockDetails)).thenReturn(input);
    GenericOutputDto outputDto = stockMgmtController.updateStockDetails(stockDetailsDto, result);
    assertTrue(null != outputDto.getMessage() && outputDto.getMessage().equals(input.getMessage()));
  }

  @Test
  public void testUpdateStockDetailsValidation() {
    input.setErrorCode(StockMgmtConstants.DATA_VALIDATION_ERROR);
    Mockito.when(result.hasErrors()).thenReturn(true);
    GenericOutputDto outputDto = stockMgmtController.updateStockDetails(stockDetailsDto, result);
    assertTrue(null != outputDto.getErrorCode()
        && StockMgmtConstants.DATA_VALIDATION_ERROR.equals(outputDto.getErrorCode()));

  }

}

