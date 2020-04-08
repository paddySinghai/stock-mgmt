/**
 * 
 */
package com.payconiq.stockMgmt.exception;

import lombok.*;

/**
 * Exception class for Stock Management Service
 * 
 * @author Padmalaya Singhai
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockMgmtBusinessServiceException extends ExceptionInInitializerError {
	private String errorCode;
	private String errorMessage;
}
