package com.web.controller;

import com.web.Model.storage.Bike;
import com.web.service.BikeSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
@CrossOrigin
public class BikeController {
	 
	  @Autowired 
	  private BikeSearchService bikeSearchService;
	  
	  @PostMapping(path="/addBike", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<Bike> addBike(@RequestBody Bike bike) {

			Bike save = bikeSearchService.addBike(bike);
			if (save != null) {
				return new ResponseEntity<>(save, HttpStatus.CREATED);
			}
		  return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	  
	  @GetMapping("/getAllBike")
	  public ResponseEntity<List<Bike>> getAllBike(){
		  List<Bike> bikes = bikeSearchService.getAllBikes();
		  if(bikes.size()>=0){
		  return new ResponseEntity<>(bikes, HttpStatus.OK);
	  }
		  return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	  }
	  
	  
	  @GetMapping("/getBikeById/{id}")
	  public Bike getBikeById(@PathVariable long id) {
		  Bike bike = bikeSearchService.getBikeById(id);
		  return bike;		  
	  }

}
