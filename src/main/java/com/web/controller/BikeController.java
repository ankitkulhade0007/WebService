package com.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.Model.storage.Bike;
import com.web.service.BikeSearchService;

@RestController
@RequestMapping("/bike")
@CrossOrigin
public class BikeController {
	 
	  @Autowired 
	  private BikeSearchService bikeSearchService;
	  
	  @PostMapping(path="/addBike", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<?> addBike(@RequestBody Bike bike) {
		  Bike save = bikeSearchService.addBike(bike);
		  return ResponseEntity.ok(save);
	  }
	  
	  @GetMapping("/getAllBike")
	  public ResponseEntity<List<Bike>> getAllBike(){
		  List<Bike> bikes = bikeSearchService.getAllBikes();
		  return ResponseEntity.ok(bikes);
	  }
	  
	  
	  @GetMapping("/getBikeById/{id}")
	  public Bike getBikeById(@PathVariable long id) {
		  Bike bike = bikeSearchService.getBikeById(id);
		  return bike;		  
	  }

}
