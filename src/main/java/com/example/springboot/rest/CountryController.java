package com.example.springboot.rest;

import com.example.springboot.service.RoutingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {

    private final RoutingService routingService;

    public CountryController(RoutingService routingService) {
        this.routingService = routingService;
    }

    @GetMapping("/routing/{origin}/{destination}")
    public ResponseEntity<List<String>> getRoute(@PathVariable String origin, @PathVariable String destination) {
        return new ResponseEntity<>(routingService.getRoute(origin, destination), HttpStatus.OK);
    }


}
