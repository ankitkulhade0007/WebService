package com.web.repo;

import com.web.Model.Enum.BikeConstant;
import com.web.Model.storage.Bike;
import com.web.Model.storage.BikeFilter;
import com.web.exception.RecordNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class BikeRepositoryImpl implements CustomBikeRepository{
    private static final Logger LOG = LoggerFactory.getLogger(BikeRepositoryImpl.class);

    private static final String BIKE = "Bike";
    private static final String PURCHASE_DATE="purchaseDate";
    private static final String NAME = "name";
    private static final String COMPANY = "company";
    private static final String CHECIS_NUMBER = "checisNumber";

    private final MongoTemplate mongoTemplate;
    public BikeRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Bike> getBikeBasedOnPurchaseDate(String date) {
        List<Bike> bikes = new ArrayList<>();
        if(!date.isEmpty()){
          bikes =   mongoTemplate.find( new Query().addCriteria(
                    where(PURCHASE_DATE).is(date)), Bike.class);
        }
        return bikes;
    }

    @Override
    public List<Bike> getBikes() {
        List<Bike> bikes = mongoTemplate.findAll(Bike.class);
        return bikes;
    }

    // find bike by Company-Name
    // sort the result by name in Ascending Order
    @Override
    public List<Bike> getBikeInAscendingOrderByCompanyName(String companyName){
        Query query = new Query();
        query.addCriteria(where(COMPANY).is(companyName))
                .with(Sort.by(Sort.Direction.ASC, "name"));
        return  mongoTemplate.find(query, Bike.class);
    }

    @Override
    public void deleteBikeById(String id) {
        Query query = new Query();
        query.addCriteria(where("id").is(id));
        Bike bike = mongoTemplate.findOne(query, Bike.class);

        if(bike != null){
            mongoTemplate.remove(query.addCriteria(where("id").is(id)), Bike.class);
        }else {
            throw new RecordNotFoundException("following id not found  "+id);
        }
    }

    // find bike where company-name not include Honda
    // Sort the result by bike-name in descending order
    @Override
    public List<Bike> getBikeInDescingingOrderByNotIncludingCompanyName(String companyName){
        Query query = new Query();
        query.addCriteria(where(COMPANY).ne(companyName))
                .with(Sort.by(Sort.Direction.DESC,"name"));
        return mongoTemplate.find(query, Bike.class);
    }

    // Different way to write query using field and value
    @Override
    public List<Bike> getBikeBasedOnFilter(List<BikeFilter> bikeFilters){
        Query query = new Query();
        bikeFilters.forEach(f-> {
            if(BikeConstant.HONDA_BIKE.equals(f.getField())){
                query.addCriteria(where(f.getField()+"_"+BIKE).in(f.getValues()));
            }else {
                query.addCriteria(where(f.getField()).in(f.getValues()));
            }
        });
        return mongoTemplate.find(query, Bike.class);
    }

    // Update Bike document
    @Override
    public void updateBike(String bikeId,  String filed, String value){
        Update update = new Update();
        update.set(filed, value);
        mongoTemplate.updateFirst(query(where("id").is(bikeId)),update, Bike.class);
    }

}
// Query, addCriteria, where, is, with, Sort, Direction, ASC, DESC, Update, set, updateFirst

// Query, addCriteria(where().is()).with(Sort.by(Sort.Direction.DESC,"name"));
// Query, addCriteria(where().is()).with(Sort.by(Sort.Direction.ASC,"name"));
/*      where().alike()
        where().all()
        where().is()
        where().and()
        where().andDocumentStructureMatches()
        where().andOperator()
        where().elemMatch()
        where().type()
        where().exists()
        where().isNullValue()
        where().regex()
        where().ne()
        */
