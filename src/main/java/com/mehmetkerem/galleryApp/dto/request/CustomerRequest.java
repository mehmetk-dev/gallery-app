package com.mehmetkerem.galleryApp.dto.request;

import com.mehmetkerem.galleryApp.models.Account;
import com.mehmetkerem.galleryApp.models.Address;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CustomerRequest {

    private String firstName;

    private String lastName;

    private String tckn;

    private Date birthDate;

    private Long accountId;

    private Long addressId;
}
