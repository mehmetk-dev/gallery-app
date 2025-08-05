package com.mehmetkerem.galleryApp.exception;

import lombok.Getter;

@Getter
public enum MessageType {
    NO_RECORD_EXIST("1004", "Kayıt bulunamadı!"),
    GENERAL_EXCEPTION("9999", "Genel bir hata oluştu!"),
    TOKEN_IS_EXPIRED("1005", "Tokenin süresi biitmiştir!"),
    USERNAME_NOT_FOUND("1995", "Kullanıcı adına ait veri bulunamadı!"),
    USER_ALL_READY_SAVED("1003", "Bu kullanıcı adıyla zaten bir kullanıcı kayıtlı!"),
    USERNAME_OR_PASSWORD_INVALID("1004", "Kullanıcı adı veya şifre yanlış!"),
    REFRESH_TOKEN_NOT_FOUND("1006", "Girilen Refresh Token bulunamadı!"),
    REFRESH_TOKEN_IS_EXPIRED("1007", "Refresh tokenin süresi dolmuştur."),
    ADDRESS_ALL_READY_SAVED("1010", "Bu adres başka biri için kayıtlı."),
    CURRENCY_RATES_IS_OCCURED("1234", "Döviz kuru alınamadı."),
    CAR_IS_SALED("1012", "Araç satılmıştır!"),
    CUSTOMER_AMOUNT_IS_NOT_ENOUGH("1011", "Müşterinin aracı almak için yeterli parası yok!");


    public final String code;
    public final String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
