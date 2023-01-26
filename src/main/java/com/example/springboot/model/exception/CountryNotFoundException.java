package com.example.springboot.model.exception;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(String countryCode) {
        super(String.format("Country with code %s not found", countryCode));
    }


}
