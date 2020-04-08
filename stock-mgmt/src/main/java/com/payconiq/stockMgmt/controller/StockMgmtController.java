package com.payconiq.stockMgmt.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.payconiq.stockMgmt.constants.StockMgmtConstants;
import com.payconiq.stockMgmt.dto.GenericOutputDto;
import com.payconiq.stockMgmt.dto.StockDetailsDto;
import com.payconiq.stockMgmt.dto.model.StockDetails;
import com.payconiq.stockMgmt.exception.StockMgmtBusinessServiceException;
import com.payconiq.stockMgmt.service.StockMgmtService;
import com.payconiq.stockMgmt.util.HelperUtil;
import com.payconiq.stockMgmt.validator.StockMgmtValidator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller class to create/update Stocks
 * 
 * @author Padmalaya Singhai
 *
 */
@RestController
@RequestMapping("/api")
public class StockMgmtController {
  @InitBinder
  public void initBinder(WebDataBinder dataBinder) {
    dataBinder.setValidator(new StockMgmtValidator());
  }

  private StockMgmtService stockMgmtService;

  @Autowired
  public StockMgmtController(StockMgmtService stockMgmtService) {
    this.stockMgmtService = stockMgmtService;
  }


  @ApiOperation(value = "Create New Stock")
  @PostMapping(value = "/stocks", headers = "Accept=application/json",
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public GenericOutputDto createNewStock(
      @ApiParam(value = "Object containing stock Details",
          required = true) @Valid @RequestBody StockDetailsDto stockDetails,
      BindingResult result) throws StockMgmtBusinessServiceException {

    if (!result.hasErrors()) {
      StockDetails sqlDto = HelperUtil.prepareSqlInput(stockDetails);
      return stockMgmtService.saveStockDetails(sqlDto);
    } else {
      GenericOutputDto output = new GenericOutputDto();
      output.setErrorCode(StockMgmtConstants.DATA_VALIDATION_ERROR);
      output.setErrorMessage(HelperUtil.prepareErrorMessages(result.getFieldErrors()));
      return output;
    }
  }

  @ApiOperation(value = "Update existing Stock Details")
  @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
      @ApiResponse(code = 200, message = "Stock Details Updated Successfully")})
  @PutMapping(value = "/stocks/{id}", headers = "Accept=application/json",
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public GenericOutputDto updateStockDetails(
      @ApiParam(value = "Object containing stock Details",
          required = true) @Valid @RequestBody StockDetailsDto stockDetails,
      BindingResult result) throws StockMgmtBusinessServiceException {

    if (!result.hasErrors()) {
      StockDetails sqlDto = HelperUtil.prepareSqlInputForUpdate(stockDetails);
      return stockMgmtService.updateStockDetails(sqlDto);
    } else {
      GenericOutputDto output = new GenericOutputDto();
      output.setErrorCode(StockMgmtConstants.DATA_VALIDATION_ERROR);
      output.setErrorMessage(HelperUtil.prepareErrorMessages(result.getFieldErrors()));
      return output;
    }
  }
}
