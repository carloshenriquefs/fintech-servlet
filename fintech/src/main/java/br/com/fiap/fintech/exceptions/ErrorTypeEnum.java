package br.com.fiap.fintech.exceptions;

public enum ErrorTypeEnum {

    ERROR_INSERTING_DATA,
    ERROR_SEARCHING_DATA,
    ERROR_UPDATING_DATA,
    ERROR_DELETING_DATA,
    ERROR_LISTING_DATA,

    ERROR_CONNECTING_TO_DATABASE,
    ERROR_FETCHING_DATA;
}