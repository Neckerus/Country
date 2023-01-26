package com.example.springboot.listener;

import com.example.springboot.helpers.CountryHelper;
import com.example.springboot.model.Country;
import com.example.springboot.model.Graph;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.springboot.helpers.CountryHelper.readCountriesFromJSON;

@Component
public class ApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<Country, List<Country>> countryMap = readCountriesFromJSON();

        CountryHelper.countryBorders = new Graph(countryMap);
        CountryHelper.countryMap = countryMap.keySet().stream().collect(Collectors.toMap(e -> e.getCountryCode(), e -> e));
    }
}
