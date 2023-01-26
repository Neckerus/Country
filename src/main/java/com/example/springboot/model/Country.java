package com.example.springboot.model;

import java.util.Objects;

public final class Country {

    private final String countryCode;

    private final Region region;

    public Country(String countryCode) {
        this.countryCode = countryCode;
        this.region = Region.NA;
    }

    public Country(String countryCode, Region region) {
        this.countryCode = countryCode;
        this.region = region;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Region getRegion() {
        return region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return countryCode.equals(country.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode);
    }
}
