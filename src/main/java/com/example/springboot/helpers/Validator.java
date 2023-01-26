package com.example.springboot.helpers;

import com.example.springboot.model.Country;
import com.example.springboot.model.exception.CountryNotFoundException;
import com.example.springboot.model.exception.NoRoadFoundException;

import static com.example.springboot.helpers.CountryHelper.countryMap;

public class Validator {

    public static void validateCountries(String origin, String destination) {
        Country startCountry = countryMap.get(origin);
        Country endCountry = countryMap.get(destination);

        if (startCountry == null) {
            throw new CountryNotFoundException(origin);
        }

        if (endCountry == null) {
            throw new CountryNotFoundException(destination);
        }

        if (!startCountry.getRegion().isConnection(endCountry.getRegion())) {
            throw new NoRoadFoundException();
        }
    }
}
