package org.ababup1192;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public PersonServiceImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Person> findByItemName(String itemName) {
        Query query = new Query(Criteria.where("loves.name").is(itemName));
        return mongoTemplate.find(query, Person.class);
    }
}
