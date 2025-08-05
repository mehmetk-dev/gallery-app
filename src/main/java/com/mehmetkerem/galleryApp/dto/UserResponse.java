package com.mehmetkerem.galleryApp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse extends BaseResponse {

    private String username;
    private String password;
}
