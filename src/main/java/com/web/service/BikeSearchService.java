package com.web.service;

import com.web.Model.storage.Bike;
import com.web.repo.BikeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BikeSearchService {
	private static final Logger LOG = LoggerFactory.getLogger(BikeSearchService.class);

	@Autowired
	private BikeRepository bikeRepository;

	public Bike getBikeById(long id) {
		List<Bike> bikeList = getAllBike();
		Optional<Bike> bike = bikeList.stream().filter(b -> b.getId() == id).findFirst();
		return bike.get();
	}

	public Bike addBike(Bike addBike) {
		LOG.info("called addBike()");
		Bike bike = bikeRepository.save(addBike);
		return bike;
	}
	
	public List<Bike> getAllBikes(){
		LOG.info("called getAllBikes()");
		List<Bike> bikes = bikeRepository.findAll();
		return bikes;
	}
	
	public List<Bike> getBikeDetails(String bikeName) {
		LOG.info("called getBikeDetails()");
		List<Bike> bikeList = getAllBike();
		bikeList = bikeList.stream().filter(bike -> bike.getName().equalsIgnoreCase(bikeName))
				.collect(Collectors.toList());
		return bikeList;
	}

	public List<Bike> getAllBike() {
		List<Bike> bikes = new ArrayList<>();
		Bike bike1 = new Bike(1, "Pulsar", "N200", "Bajaj", "101", "102", "01012020");
		Bike bike2 = new Bike(2, "Pulsar", "N160", "Bajaj", "201", "202", "01012021");
		Bike bike3 = new Bike(3, "Pulsar", "F250", "Bajaj", "303", "303", "01012020");
		Bike bike4 = new Bike(4, "Pulsar", "N250", "Bajaj", "404", "406", "01012022");
		Bike bike5 = new Bike(5, "Dominor", "250", "Bajaj", "505", "505", "01012020");

		bikes.add(bike1);
		bikes.add(bike2);
		bikes.add(bike3);
		bikes.add(bike4);
		bikes.add(bike5);
		return bikes;
	}
}
