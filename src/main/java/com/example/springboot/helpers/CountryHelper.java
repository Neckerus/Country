package com.example.springboot.helpers;


import com.example.springboot.model.Country;
import com.example.springboot.model.Graph;
import com.example.springboot.model.Region;
import org.json.JSONArray;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CountryHelper {

    public static Graph countryBorders;

    public static Map<String, Country> countryMap;

    public static Map<Country, List<Country>> readCountriesFromJSON() {
        Map<Country, List<Country>> countryMap = Collections.EMPTY_MAP;
        try {
            String exampleRequest = new String(Files.readAllBytes(new ClassPathResource("countries.json").getFile().toPath()), StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(exampleRequest);
            List<Map<String, Object>> jsonList = jsonArray.toList().stream().map(m -> ((HashMap<String, Object>) m)).collect(Collectors.toList());

            countryMap = jsonList.stream().collect(Collectors
                    .toMap(e -> new Country(e.get("cca3").toString(), Region.valueOf(e.get("region").toString().toUpperCase()))
                            , f -> ((List<String>) f.get("borders")).stream().map(c -> new Country(c)).collect(Collectors.toList())));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return countryMap;
    }

}
