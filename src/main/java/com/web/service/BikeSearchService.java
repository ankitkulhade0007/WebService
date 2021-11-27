package com.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.Model.storage.Bike;
import com.web.repo.BikeRepository;

@Service
public class BikeSearchService {

	@Autowired
	private BikeRepository bikeRepository;
	
	
	public Bike getBikeById(long id) {
		List<Bike> bikeList = getAllBike();
		Optional<Bike> bike = bikeList.stream().filter(b -> b.getId() == id).findFirst();
		return bike.get();
	}

	public Bike addBike(Bike addBike) {
		Bike bike = bikeRepository.save(addBike);
		return bike;
	}
	
	public List<Bike> getAllBikes(){
		List<Bike> bikes = bikeRepository.findAll();
		return bikes;
	}
	
	public List<Bike> getBikeDetails(String bikeName) {
		List<Bike> bikeList = getAllBike();
		bikeList = bikeList.stream().filter(bike -> bike.getName().equalsIgnoreCase(bikeName))
				.collect(Collectors.toList());
		return bikeList;
	}

	public List<Bike> getAllBike() {
		List<Bike> bikes = new ArrayList<>();
		Bike bike1 = new Bike(1, "Pulsar", "N200", "Bajaj", "101", "102");
		Bike bike2 = new Bike(2, "Pulsar", "N160", "Bajaj", "201", "202");
		Bike bike3 = new Bike(3, "Pulsar", "F250", "Bajaj", "303", "303");
		Bike bike4 = new Bike(4, "Pulsar", "N250", "Bajaj", "404", "406");
		Bike bike5 = new Bike(5, "Dominor", "250", "Bajaj", "505", "505");

		bikes.add(bike1);
		bikes.add(bike2);
		bikes.add(bike3);
		bikes.add(bike4);
		bikes.add(bike5);
		return bikes;
	}
}
