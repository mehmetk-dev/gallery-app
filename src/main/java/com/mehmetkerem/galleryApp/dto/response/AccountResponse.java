package com.mehmetkerem.galleryApp.dto.response;

import com.mehmetkerem.galleryApp.dto.BaseResponse;
import com.mehmetkerem.galleryApp.enums.CurrencyType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountResponse extends BaseResponse {
    private String accountNo;

    private String iban;

    private BigDecimal amount;

    private CurrencyType currencyType;
}
