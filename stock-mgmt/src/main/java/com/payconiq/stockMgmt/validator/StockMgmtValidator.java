package com.payconiq.stockMgmt.validator;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.payconiq.stockMgmt.constants.StockMgmtConstants;
import com.payconiq.stockMgmt.dto.StockDetailsDto;
import com.payconiq.stockMgmt.util.CommonUtil;

public class StockMgmtValidator implements Validator {

  @Override
  public boolean supports(Class<?> params) {
    return StockDetailsDto.class.equals(params);
  }

  @Override
  public void validate(Object arg, Errors errors) {
    StockDetailsDto obj = (StockDetailsDto) arg;
    if (!StringUtils.isEmpty(obj.getName()) && !obj.getName().matches("^[a-zA-Z ]*$")) {
      errors.rejectValue("name", StockMgmtConstants.INVALID_NAME);
    } else if (StringUtils.isEmpty(obj.getName())) {
      errors.rejectValue("name", StockMgmtConstants.NAME_EMPTY);
    }

    if (!StringUtils.isEmpty(obj.getCurrentPrice())
        && !CommonUtil.isNotNullAndNotZero(obj.getCurrentPrice())) {
      errors.rejectValue("currentPrice", StockMgmtConstants.INVALID_AMOUNT);
    } else if (StringUtils.isEmpty(obj.getCurrentPrice())) {
      errors.rejectValue("currentPrice", StockMgmtConstants.AMOUNT_EMPTY);
    }
  }
}
