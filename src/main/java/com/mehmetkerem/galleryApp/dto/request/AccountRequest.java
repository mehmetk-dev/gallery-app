package com.mehmetkerem.galleryApp.dto.request;

import com.mehmetkerem.galleryApp.enums.CurrencyType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountRequest {

    @NotEmpty
    private String accountNo;

    @NotEmpty
    private String iban;

    @NotNull(message = "Tutar boş olamaz")
    private BigDecimal amount;

    @NotNull
    private CurrencyType currencyType;
}
