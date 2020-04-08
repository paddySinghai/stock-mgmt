package com.payconiq.stockMgmt.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.payconiq.stockMgmt.dto.GenericOutputDto;

/**
 * Exception Handler for Stock Management Module
 * 
 * @author Padmalaya Singhai
 *
 */
@ControllerAdvice
public class StockMgmtServiceExceptionController {
	@ExceptionHandler(value = StockMgmtBusinessServiceException.class)
	public ResponseEntity<Object> exception(StockMgmtBusinessServiceException exception) {
		GenericOutputDto outputDto = new GenericOutputDto();
		outputDto.setErrorCode(exception.getErrorCode());
		outputDto.setErrorMessage(exception.getErrorMessage());
		return ResponseEntity.badRequest().body(outputDto);
	}
}
