package com.example.springboot.service;

import com.example.springboot.helpers.CountryHelper;
import com.example.springboot.model.Country;
import com.example.springboot.model.Graph;
import com.example.springboot.model.exception.CountryNotFoundException;
import com.example.springboot.model.exception.NoRoadFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.springboot.helpers.CountryHelper.readCountriesFromJSON;
import static org.junit.jupiter.api.Assertions.*;

class RoutingServiceTest {

    @BeforeAll
    public static void init() {
        Map<Country, List<Country>> countryListMap = readCountriesFromJSON();

        CountryHelper.countryBorders = new Graph(countryListMap);
        CountryHelper.countryMap = countryListMap.keySet().stream().collect(Collectors.toMap(e -> e.getCountryCode(), e -> e));
    }


    @Test
    public void shouldFindRoadToNeighbour() {
        RoutingService rs = new RoutingService();
        List<String> result = Arrays.asList("POL", "RUS");

        List<String> road = rs.getRoute("POL", "RUS");

        assertEquals(road.size(), 2);
        assertEquals(road, result);
    }

    @Test
    public void shouldFindRoadToCountryOnSameContinent() {
        RoutingService rs = new RoutingService();
        List<String> result = Arrays.asList("CZE", "AUT", "ITA");

        List<String> road = rs.getRoute("CZE", "ITA");

        assertEquals(road.size(), 3);
        assertEquals(result, road);
    }

    @Test
    public void shouldFindRoadtoCountryOnDifferentContinent() {
        RoutingService rs = new RoutingService();
        List<String> result = Arrays.asList("LSO", "ZAF", "BWA", "ZMB", "COD", "CAF", "SDN", "EGY",
                "ISR", "JOR", "IRQ", "IRN", "AFG", "CHN");

        List<String> road = rs.getRoute("LSO", "CHN");

        assertEquals(road.size(), 14);
        assertEquals(result, road);
    }

    @Test
    public void shouldThrowNoRoadFoundOnSameContinent() {
        NoRoadFoundException thrown = assertThrows(
                NoRoadFoundException.class,
                () -> new RoutingService().getRoute("ISL", "POL")
        );

        assertTrue(thrown.getMessage().contentEquals("No road found"));
    }

    @Test
    public void shouldThrowNoRoadFoundOnExcludingContinents() {
        NoRoadFoundException thrown = assertThrows(
                NoRoadFoundException.class,
                () -> new RoutingService().getRoute("AUS", "POL")
        );

        assertTrue(thrown.getMessage().contentEquals("No road found"));
    }

    @Test
    public void shouldThrowCountryNotFoundException() {
        CountryNotFoundException thrown = assertThrows(
                CountryNotFoundException.class,
                () -> new RoutingService().getRoute("ASS", "POL")
        );

        assertTrue(thrown.getMessage().contentEquals("Country with code ASS not found"));
    }
}