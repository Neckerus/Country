package com.example.springboot.service;

import com.example.springboot.model.Country;
import com.example.springboot.model.Graph;
import com.example.springboot.model.exception.NoRoadFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.springboot.helpers.CountryHelper.countryBorders;
import static com.example.springboot.helpers.CountryHelper.countryMap;
import static com.example.springboot.helpers.Validator.validateCountries;

@Service
public class RoutingService {

    public List<String> getRoute(String origin, String destination) {
        validateCountries(origin, destination);

        List<String> returnSet = getRoute(countryBorders, origin, destination);

        if (returnSet.isEmpty()) {
            throw new NoRoadFoundException();
        }

        return returnSet;
    }


    private List<String> getRoute(Graph graph, String origin, String destination) {
        Map<String, String> predecessor = new HashMap<>();
        Set<String> visited = new LinkedHashSet<>();
        Queue<String> toVisit = new LinkedList<>();
        toVisit.add(origin);
        visited.add(origin);
        while (!toVisit.isEmpty()) {
            String vertex = toVisit.poll();
            for (Country v : graph.getBorders().get(countryMap.get(vertex))) {
                if (!visited.contains(v.getCountryCode())) {
                    visited.add(v.getCountryCode());
                    toVisit.add(v.getCountryCode());
                    predecessor.put(v.getCountryCode(), vertex);
                }
                if (v.getCountryCode().equals(destination)) {
                    return getShortestRoute(predecessor, origin, destination);
                }
            }
        }
        return Collections.emptyList();
    }

    private List<String> getShortestRoute(Map<String, String> predecessor, String origin, String destination) {
        List<String> result = new LinkedList<>();
        result.add(destination);
        String firstCode = destination;
        do {
            firstCode = predecessor.get(firstCode);
            result.add(0, firstCode);
        } while (!firstCode.equals(origin));

        return result;
    }
}
