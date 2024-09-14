package com.iot.CloudAnalytics;


import java.util.Arrays;
import java.util.Iterator;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

public class MongodbInterface<document> {

	MongoClient mongo = new MongoClient( "localhost" , 27017 );

	
		public void createfooddatabase() {
		
		// Creating a Mongo client
		
		// Accessing the database
		MongoDatabase database = mongo.getDatabase("foodDB");
		
		// Creating a collection
		if(database.getCollection("foodcalories")!=null)
		{
			database.createCollection("foodcalories");
	    	System.out.println(" Food calories created successfully");
		}
		else
		{
			System.out.println("Food calories collection already exists !");
		}
		
			}
		
  public void addEnergyData(String energydata , Energydata dataobj)
  {
		// Retrieving a collection
		MongoDatabase database = mongo.getDatabase("energyDB");

//		String title =  dataobj.getTitle();
//		System.out.println(title);
		
	//	int votes =  dataobj.getVote_count();
	//	System.out.println(votes);
			
				MongoCollection<Document> collection = database.getCollection("energydata");
				System.out.println("Collection Energydata selected successfully");
			//	String bpath = dataobj.getBackdrop_path();
			//	System.out.println(bpath);

				Document document = new Document("EnergyData", "ZigbeeEnergyProfile")
				.append("meterid", dataobj.getMeterid())
				.append("timestmap", dataobj.getTimestamp())
				.append("reactivepower", dataobj.getReactivepower())
				.append("RMSCurrent", dataobj.getRmscurrent());
				collection.insertOne(document);
				System.out.println("Document inserted successfully");

  }
  
  
  
	public void readenergydata()
	{
		System.out.println("Retreiving Energy data ");
		
		MongoClient mongo = new MongoClient( "localhost" , 27017 );


		// Getting the iterable object
			    MongoDatabase database = mongo.getDatabase("energyDB");

	        	MongoCollection<Document> collection = database.getCollection("energydata");
	        
				FindIterable<Document> iterDoc = collection.find();
				int i = 1;
				// Getting the iterator
				Iterator it = iterDoc.iterator();
				while (it.hasNext()) {
					System.out.println(it.next());
					i++;
				}
			//	findenergyanomaly(collection);
				aggregateEnergyData(collection);

	}
	
	public void findenergyanomaly(MongoCollection<Document> collection)
	{
		
		    BasicDBObject getQuery = new BasicDBObject();
		    getQuery.put("reactivepower", new BasicDBObject("$gt", 200));
		    FindIterable<Document> iterDoc = collection.find(getQuery);
		    int i =1;
			Iterator it = iterDoc.iterator();

		    while (it.hasNext()) {
				System.out.println(it.next());
				i++;
			}
	}
	
	
	@SuppressWarnings("deprecation")
	public void aggregateEnergyData(MongoCollection<Document> collection)
	{
		    BasicDBObject getQuery = new BasicDBObject();
		    getQuery.put("reactivepower", new BasicDBObject("$gt", 200));
		    FindIterable<Document> iterDoc = collection.find(getQuery);
		    int i =1;
			Iterator it = iterDoc.iterator();
		    MongoDatabase db = mongo.getDatabase("energyDB");

			db.getCollection("energydata").aggregate(
					Arrays.asList(
					Aggregates.match(Filters.gt("reactivepower", 200)),
					Aggregates.project(
					Projections.fields(
					Projections.excludeId(),
					Projections.include("foodName","category")
					)
					)
					)
					);
				//	).forEach((Block<? super Document>) System.out::println);

		/*    while (it.hasNext()) {
				System.out.println(it.next());
				i++;
			}*/
		
		
	}

}
