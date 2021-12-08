package com.web.repo;

import com.web.Model.storage.Bike;
import com.web.Model.storage.BikeFilter;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomBikeRepository{

    List<Bike> getBikeBasedOnPurchaseDate(String date);
    List<Bike> getBikes();
    void updateBike(String bikeId, String filed, String fieldValue);
    List<Bike> getBikeBasedOnFilter(List<BikeFilter> bikeFilterList);
    List<Bike> getBikeInDescingingOrderByNotIncludingCompanyName(String companyName);
    List<Bike> getBikeInAscendingOrderByCompanyName(String CompanyName);
    void deleteBikeById(String id);
}
