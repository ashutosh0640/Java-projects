package com.monitor.multipinger.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.CollectionOptions.TimeSeriesOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class MongoDBInitializer {
	
	@Autowired
	private MongoTemplate  mongoTemplate;
	
    @PostConstruct 
    public void createTimeSeriesCollection() {  
        
        mongoTemplate.createCollection("logs", CollectionOptions.empty()  
                .timeSeries(TimeSeriesOptions.timeSeries("timestamp"))  
                .maxDocuments(1000)
        );
    }

}
