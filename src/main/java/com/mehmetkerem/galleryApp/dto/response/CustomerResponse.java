package com.mehmetkerem.galleryApp.dto.response;

import com.mehmetkerem.galleryApp.dto.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CustomerResponse extends BaseResponse {

    private String firstName;

    private String lastName;

    private String tckn;

    private Date birthDate;

    private AccountResponse account;

    private AddressResponse address;

}
