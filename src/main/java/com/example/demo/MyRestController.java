package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
public class MyRestController {

    @Autowired
    LocationRepository locationRepository;

    @RequestMapping(value = "/locations", method = RequestMethod.POST)
    public ResponseEntity<Void> saveLocation(@RequestBody Location l, UriComponentsBuilder b) {

        this.locationRepository.save(l);

        UriComponents uriComponents = b.path("/locations/{id}").buildAndExpand(l.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/locations", method = RequestMethod.GET)
    public Iterable<Location> fetchLocation() {
        return locationRepository.findAll();
    }

    @RequestMapping(value = "/locations/{locationId}", method = RequestMethod.GET)
    public Optional<Location> fetchCustomer(@PathVariable int locationId) {
        return locationRepository.findById(locationId);
    }

    @RequestMapping(value = "/locations/{locationId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCustomer(@PathVariable int locationId) {
        locationRepository.deleteById(locationId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
